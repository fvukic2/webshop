package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.AddressRequest;
import com.fvukic.webshop.domain.entity.Address;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.repository.AddressRepository;
import com.fvukic.webshop.service.AddressService;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {
    
    private AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public void saveNewAddressRequest(AddressRequest addressRequest) {
        Address address = getAddress(addressRequest);
        addressRepository.save(address);
    }

    @Override
    public void updateAddressRequest(AddressRequest addressRequest, Integer addressId) {
        Address existingAddress = addressRepository.findById(addressId).
                orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,addressId));
        existingAddress.setAddress(addressRequest.getAddress());
        existingAddress.setPostalCode(addressRequest.getPostalCode());
    }

    @Override
    public void deleteAddressRequest(Integer addressId) {
        if (!addressRepository.existsById(addressId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,addressId);
        }
        addressRepository.deleteById(addressId);
    }
    
    private Address getAddress(AddressRequest addressRequest){
        return Address.builder().address(addressRequest.getAddress()).
                postalCode(addressRequest.getPostalCode()).build();
    }
}
