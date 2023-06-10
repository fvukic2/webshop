package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.CityRequest;
import com.fvukic.webshop.domain.entity.City;
import com.fvukic.webshop.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    private List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @PostMapping
    private void saveNewCityRequest(@RequestBody CityRequest cityRequest){
        cityService.saveNewCityRequest(cityRequest);
    }

    @PutMapping("/{cityId}")
    private void updateCityRequest(@PathVariable Integer cityId, @RequestBody CityRequest cityRequest){
        cityService.updateCityRequest(cityRequest,cityId);
    }

    @DeleteMapping("/{cityId}")
    private void deleteCityRequest(@PathVariable Integer cityId){
        cityService.deleteCityRequest(cityId);
    }
}
