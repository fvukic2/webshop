package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.CustomerRequest;
import com.fvukic.webshop.domain.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveNewCustomerRequest(CustomerRequest customerRequest);

    void updateCustomerRequest(CustomerRequest customerRequest,Integer customerId);

    void deleteCustomerRequest(Integer id);
}
