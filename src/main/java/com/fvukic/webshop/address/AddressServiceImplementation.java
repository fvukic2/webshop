package com.fvukic.webshop.address;

import com.fvukic.webshop.address.model.AddressRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {
    
    private AddressRepository addressRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImplementation.class);

    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        logger.info("Fetching all addresses");
        List<Address> addresses = addressRepository.findAll();
        logger.info("Fetched {} addresses", addresses.size());
        return addresses;
    }

    @Override
    public void saveNewAddressRequest(AddressRequest addressRequest) {
        logger.info("Saving new address request: {}", addressRequest);
        Address address = getAddress(addressRequest);
        addressRepository.save(address);
        logger.info("New address request saved: {}", address);
    }

    @Override
    public void updateAddressRequest(AddressRequest addressRequest, Integer addressId) {
        logger.info("Updating address request: {} with ID: {}", addressRequest, addressId);
        Address existingAddress;
        try {
            existingAddress = addressRepository.findById(addressId).
                    orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,addressId));
        } catch (EntityWithIdNotFoundException e) {
            logger.error("Error occurred while updating address request: {}", e.getMessage());
            throw e;
        }
        existingAddress.setAddress(addressRequest.getAddress());
        existingAddress.setPostalCode(addressRequest.getPostalCode());
        existingAddress.setCity(addressRequest.getCity());
        addressRepository.save(existingAddress);
        logger.info("Address request updated: {}", existingAddress);
    }

    @Override
    public void deleteAddressRequest(Integer addressId) {
        logger.info("Deleting address with ID: {}", addressId);
        if (!addressRepository.existsById(addressId)){
            logger.error("Error occurred while deleting address: Address with ID {} not found", addressId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,addressId);
        }
        addressRepository.deleteById(addressId);
        logger.info("Deleted address with ID: {}", addressId);
    }
    
    private Address getAddress(AddressRequest addressRequest){
        logger.info("Getting article from request");
        Address address = Address.builder().address(addressRequest.getAddress()).
                postalCode(addressRequest.getPostalCode()).city(addressRequest.getCity())
                .build();
        logger.info("Got article: {}", address.getAddress());
        return address;
    }
}
