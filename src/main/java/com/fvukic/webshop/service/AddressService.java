package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.AddressRequest;
import com.fvukic.webshop.domain.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    void saveNewAddressRequest(AddressRequest addressRequest);

    void updateAddressRequest(AddressRequest addressRequest,Integer addressId);

    void deleteAddressRequest(Integer addressId);
}
