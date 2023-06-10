package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    void saveNewCityRequest(ArticleRequest articleRequest);

    void deleteCityRequest(Integer cityId);

    void updateCityRequest(ArticleRequest articleRequest,Integer cityId);
}
