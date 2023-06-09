package com.fvukic.webshop.city;

import com.fvukic.webshop.city.model.CityRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImplementation implements CityService {

    private CityRepository cityRepository;

    public CityServiceImplementation(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveNewCityRequest(CityRequest cityRequest) {
        City city = getCityRequest(cityRequest);
        cityRepository.save(city);
    }


    @Override
    public void updateCityRequest(CityRequest cityRequest, Integer cityId) {
        City existingCity = cityRepository.findById(cityId).
                orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,cityId));
        existingCity.setCity(cityRequest.getCity());
        existingCity.setCountry(cityRequest.getCountry());
        cityRepository.save(existingCity);
    }

    @Override
    public void deleteCityRequest(Integer cityId) {
        if (!cityRepository.existsById(cityId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,cityId);
        }
        cityRepository.deleteById(cityId);
    }

    private City getCityRequest(CityRequest cityRequest){
        return City.builder().city(cityRequest.getCity()).
                country(cityRequest.getCountry()).build();
    }
}
