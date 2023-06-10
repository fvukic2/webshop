package com.fvukic.webshop.customer;

import com.fvukic.webshop.customer.model.CustomerRequest;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveNewCustomerRequest(CustomerRequest customerRequest);

    void updateCustomerRequest(CustomerRequest customerRequest,Integer customerId);

    void deleteCustomerRequest(Integer customerId);
}
