package com.fvukic.webshop.country;

import com.fvukic.webshop.city.City;
import com.fvukic.webshop.country.model.CountryRequest;

import java.util.List;

public interface CountryService {

    List<City> getAllCountries();

    void saveNewCountryRequest(CountryRequest countryRequest);

    void deleteCountryRequest(Integer countryId);

    void updateCountryRequest(CountryRequest countryRequest,Integer countryId);
}
