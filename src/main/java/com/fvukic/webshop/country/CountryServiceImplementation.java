package com.fvukic.webshop.country;

import com.fvukic.webshop.country.model.CountryRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplementation implements CountryService {

    private CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void saveNewCountryRequest(CountryRequest countryRequest) {
        Country country = getCountryRequest(countryRequest);
        countryRepository.save(country);
    }

    @Override
    public void deleteCountryRequest(Integer countryId) {
        if (!countryRepository.existsById(countryId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,countryId);
        }
        countryRepository.deleteById(countryId);
    }

    @Override
    public void updateCountryRequest(CountryRequest countryRequest, Integer countryId) {
        Country existingCountry = countryRepository.findById(countryId).
                orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,countryId));
        existingCountry.setCountry(countryRequest.getCountry());
        countryRepository.save(existingCountry);
    }

    private Country getCountryRequest(CountryRequest countryRequest){
        return Country.builder().country(countryRequest.getCountry()).build();
    }
}
