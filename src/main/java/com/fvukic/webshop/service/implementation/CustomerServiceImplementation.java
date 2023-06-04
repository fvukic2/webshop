package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.CustomerRequest;
import com.fvukic.webshop.domain.entity.Customer;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.repository.CustomerRepository;
import com.fvukic.webshop.service.CustomerService;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void saveNewCustomerRequest(CustomerRequest customerRequest) {
        Customer customer = getCustomerRequest(customerRequest);
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomerRequest(CustomerRequest customerRequest,Integer customerId) {
        Customer existingCustomer = customerRepository.findById(customerId).
                orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,customerId));
        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setEmailAddress(customerRequest.getEmailAddress());
        customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomerRequest(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    private Customer getCustomerRequest(CustomerRequest customerRequest){
        return Customer.builder().firstName(customerRequest.getFirstName()).
                lastName(customerRequest.getLastName()).
                emailAddress(customerRequest.getEmailAddress()).build();
    }
}