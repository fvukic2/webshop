package com.fvukic.webshop.city;

import com.fvukic.webshop.city.model.CityRequest;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    void saveNewCityRequest(CityRequest cityRequest);

    void deleteCityRequest(Integer cityId);

    void updateCityRequest(CityRequest cityRequest,Integer cityId);
}
