package com.fvukic.webshop.address.model;

import com.fvukic.webshop.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRequest {

    private String address;

    private String postalCode;

    private City city;

}
