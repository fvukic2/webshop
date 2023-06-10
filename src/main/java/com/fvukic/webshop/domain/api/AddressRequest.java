package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.City;
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
