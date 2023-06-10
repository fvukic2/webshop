package com.fvukic.webshop.address;

import com.fvukic.webshop.address.model.AddressRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new AddressRequest to database", notes = "Enter all AddressRequest parameters to add new AddressRequest object to database", response = AddressRequest.class)
    private void saveNewAddressRequest(@Valid @RequestBody AddressRequest addressRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        addressService.saveNewAddressRequest(addressRequest);
    }

    @PutMapping("/{addressId}")
    @ApiOperation(value = "Updates AddressRequest in database", notes = "Enter AddressRequest id to update AddressRequest object in database", response = AddressRequest.class)
    private void updateAddressRequest(@Valid @PathVariable Integer addressId, @RequestBody AddressRequest addressRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        addressService.updateAddressRequest(addressRequest,addressId);
    }

    @DeleteMapping("/{addressId}")
    private void deleteAddressRequest(@PathVariable Integer addressId){
        addressService.deleteAddressRequest(addressId);
    }
}
