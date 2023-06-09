package com.fvukic.webshop.city.model;

import com.fvukic.webshop.country.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityRequest {

    @NotBlank
    private String city;

    private Country country;
}
