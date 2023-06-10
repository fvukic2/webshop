package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.CityRequest;
import com.fvukic.webshop.domain.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    void saveNewCityRequest(CityRequest cityRequest);

    void deleteCityRequest(Integer cityId);

    void updateCityRequest(CityRequest cityRequest,Integer cityId);
}
