package com.fvukic.webshop.country;

import com.fvukic.webshop.country.model.CountryRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    private List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }

    @PostMapping
    @ApiOperation(value = "Adds new CountryRequest to database", notes = "Enter all CountryRequest parameters to add new CountryRequest object to database", response = CountryRequest.class)
    private void saveNewCountryRequest(@ApiParam(value = "CountryRequest value you pass to the database") @Valid @RequestBody CountryRequest countryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        countryService.saveNewCountryRequest(countryRequest);
    }

    @PutMapping("/{countryId}")
    @ApiOperation(value = "Updates CountryRequest in database", notes = "Enter CountryRequest id to update CountryRequest object in database", response = CountryRequest.class)
    private void updateCountryRequest(@ApiParam(value = "CountryRequest value you pass to the database") @Valid @PathVariable Integer countryId, @RequestBody CountryRequest countryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        countryService.updateCountryRequest(countryRequest,countryId);
    }

    @DeleteMapping("/{countryId}")
    private void deleteCountryRequest(@PathVariable Integer countryId){
        countryService.deleteCountryRequest(countryId);
    }
}
