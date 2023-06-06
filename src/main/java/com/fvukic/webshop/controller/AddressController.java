package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.AddressRequest;
import com.fvukic.webshop.domain.entity.Address;
import com.fvukic.webshop.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    private List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @PostMapping
    private void saveNewAddressRequest(@RequestBody AddressRequest addressRequest){
        addressService.saveNewAddressRequest(addressRequest);
    }

    @PutMapping("/{addressId}")
    private void updateAddressRequest(@PathVariable Integer addressId, @RequestBody AddressRequest addressRequest){
        addressService.updateAddressRequest(addressRequest,addressId);
    }

    @DeleteMapping("/{addressId}")
    private void deleteAddressRequest(@PathVariable Integer addressId){
        addressService.deleteAddressRequest(addressId);
    }
}
