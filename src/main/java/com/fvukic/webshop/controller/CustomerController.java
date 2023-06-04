package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.CustomerRequest;
import com.fvukic.webshop.domain.entity.Customer;
import com.fvukic.webshop.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Adds new CustomerRequest to database", notes = "Enter all CustomerRequest parameters to add new CustomerRequest object to database", response = CustomerRequest.class)
    private void saveNewCustomerRequest(@ApiParam(value = "CustomerRequest value you pass to the database")@RequestBody CustomerRequest customerRequest){
        customerService.saveNewCustomerRequest(customerRequest);
    }

    @PutMapping("/{customerId}")
    @ApiOperation(value = "Updates CustomerRequest in database", notes = "Enter CustomerRequest id to update CustomerRequest object in database", response = CustomerRequest.class)
    private void updateCustomerRequest(@ApiParam(value = "CustomerRequest value you pass to the database")@PathVariable Integer customerId, @RequestBody CustomerRequest customerRequest){
        customerService.updateCustomerRequest(customerRequest,customerId);
    }

    @DeleteMapping("/{customerId}")
    private void deleteCustomerRequest(@PathVariable Integer customerId){
        customerService.deleteCustomerRequest(customerId);
    }
}
