package com.fvukic.webshop.address;

import com.fvukic.webshop.address.model.AddressRequest;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    void saveNewAddressRequest(AddressRequest addressRequest);

    void updateAddressRequest(AddressRequest addressRequest,Integer addressId);

    void deleteAddressRequest(Integer addressId);
}
