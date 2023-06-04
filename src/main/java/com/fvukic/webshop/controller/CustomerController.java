package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.CustomerRequest;
import com.fvukic.webshop.domain.entity.Customer;
import com.fvukic.webshop.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    private void saveNewCustomerRequest(@RequestBody CustomerRequest customerRequest){
        customerService.saveNewCustomerRequest(customerRequest);
    }

    @PutMapping("/{customerId}")
    private void updateCustomerRequest(@PathVariable Integer customerId, @RequestBody CustomerRequest customerRequest){
        customerService.updateCustomerRequest(customerRequest,customerId);
    }

    @DeleteMapping("/{customerId}")
    private void deleteCustomerRequest(@PathVariable Integer customerId){
        customerService.deleteCustomerRequest(customerId);
    }
}
