package com.fvukic.webshop.country;

import com.fvukic.webshop.country.model.CountryRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplementation implements CountryService {

    private CountryRepository countryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImplementation.class);

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        logger.info("Fetching all countries");
        List<Country> countries = countryRepository.findAll();
        logger.info("Fetched {} countries", countries.size());
        return countries;
    }

    @Override
    public void saveNewCountryRequest(CountryRequest countryRequest) {
        logger.info("Saving new country request");
        Country country = getCountryRequest(countryRequest);
        countryRepository.save(country);
        logger.info("Saved new country: {}", country.getCountry());
    }

    @Override
    public void deleteCountryRequest(Integer countryId) {
        logger.info("Deleting country with ID: {}", countryId);
        if (!countryRepository.existsById(countryId)){
            logger.error("Error occurred while deleting country: Country with ID {} not found", countryId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, countryId);
        }
        countryRepository.deleteById(countryId);
        logger.info("Deleted country with ID: {}", countryId);
    }

    @Override
    public void updateCountryRequest(CountryRequest countryRequest, Integer countryId) {
        logger.info("Updating country with ID: {}", countryId);
        Country existingCountry = countryRepository.findById(countryId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating country: Country with ID {} not found", countryId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, countryId);
                });
        existingCountry.setCountry(countryRequest.getCountry());
        countryRepository.save(existingCountry);
        logger.info("Updated country with ID: {}", countryId);
    }

    private Country getCountryRequest(CountryRequest countryRequest){
        logger.info("Getting country from request");
        Country country = Country.builder().country(countryRequest.getCountry()).build();
        logger.info("Got country: {}", country.getCountry());
        return country;
    }
}
