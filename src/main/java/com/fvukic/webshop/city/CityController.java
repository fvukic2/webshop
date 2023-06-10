package com.fvukic.webshop.city;

import com.fvukic.webshop.city.model.CityRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new CityRequest to database", notes = "Enter all CityRequest parameters to add new CityRequest object to database", response = CityRequest.class)
    private void saveNewCityRequest(@ApiParam(value = "CityRequest value you pass to the database")@Valid @RequestBody CityRequest cityRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        cityService.saveNewCityRequest(cityRequest);
    }

    @PutMapping("/{cityId}")
    @ApiOperation(value = "Updates CityRequest in database", notes = "Enter CityRequest id to update CityRequest object in database", response = CityRequest.class)
    private void updateCityRequest(@ApiParam(value = "CityRequest value you pass to the database")@Valid @PathVariable Integer cityId, @RequestBody CityRequest cityRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        cityService.updateCityRequest(cityRequest,cityId);
    }

    @DeleteMapping("/{cityId}")
    private void deleteCityRequest(@PathVariable Integer cityId){
        cityService.deleteCityRequest(cityId);
    }
}
