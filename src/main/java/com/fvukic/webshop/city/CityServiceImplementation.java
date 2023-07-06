package com.fvukic.webshop.city;

import com.fvukic.webshop.city.model.CityRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImplementation implements CityService {

    private CityRepository cityRepository;

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImplementation.class);

    public CityServiceImplementation(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        logger.info("Fetching all cities");
        List<City> cities = cityRepository.findAll();
        logger.info("Fetched {} cities", cities.size());
        return cities;
    }

    @Override
    public void saveNewCityRequest(CityRequest cityRequest) {
        logger.info("Saving new city request");
        City city = getCityRequest(cityRequest);
        cityRepository.save(city);
        logger.info("Saved new city: {}", city.getCity());
    }


    @Override
    public void updateCityRequest(CityRequest cityRequest, Integer cityId) {
        logger.info("Updating city with ID: {}", cityId);
        City existingCity = cityRepository.findById(cityId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating city: City with ID {} not found", cityId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, cityId);
                });
        existingCity.setCity(cityRequest.getCity());
        existingCity.setCountry(cityRequest.getCountry());
        cityRepository.save(existingCity);
        logger.info("Updated city with ID: {}", cityId);
    }

    @Override
    public void deleteCityRequest(Integer cityId) {
        logger.info("Deleting city with ID: {}", cityId);
        if (!cityRepository.existsById(cityId)){
            logger.error("Error occurred while deleting city: City with ID {} not found", cityId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, cityId);
        }
        cityRepository.deleteById(cityId);
        logger.info("Deleted city with ID: {}", cityId);
    }

    private City getCityRequest(CityRequest cityRequest){
        logger.info("Getting city from request");
        City city = City.builder().city(cityRequest.getCity()).
                country(cityRequest.getCountry()).build();
        logger.info("Got city: {}", city.getCity());
        return city;
    }
}
