package com.fvukic.webshop.country;

import com.fvukic.webshop.country.model.CountryRequest;
import org.springframework.web.bind.annotation.*;

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
    private void saveNewCountryRequest(@RequestBody CountryRequest countryRequest){
        countryService.saveNewCountryRequest(countryRequest);
    }

    @PutMapping("/{countryId}")
    private void updateCountryRequest(@PathVariable Integer countryId, @RequestBody CountryRequest countryRequest){
        countryService.updateCountryRequest(countryRequest,countryId);
    }

    @DeleteMapping("/{countryId}")
    private void deleteCountryRequest(@PathVariable Integer countryId){
        countryService.deleteCountryRequest(countryId);
    }
}
