package com.fvukic.webshop.customer;

import com.fvukic.webshop.customer.model.CustomerRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImplementation.class);

    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        List<Customer> customers = customerRepository.findAll();
        logger.info("Fetched {} customers", customers.size());
        return customers;
    }

    @Override
    public void saveNewCustomerRequest(CustomerRequest customerRequest) {
        logger.info("Saving new customer request");
        Customer customer = getCustomerRequest(customerRequest);
        customerRepository.save(customer);
        logger.info("Saved new customer: {}", customer);
    }

    @Override
    public void updateCustomerRequest(CustomerRequest customerRequest,Integer customerId) {
        logger.info("Updating customer with ID: {}", customerId);
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating customer: Customer with ID {} not found", customerId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,customerId);
                });
        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());
        existingCustomer.setEmailAddress(customerRequest.getEmailAddress());
        existingCustomer.setAddress(customerRequest.getAddress());
        customerRepository.save(existingCustomer);
        logger.info("Updated customer with ID: {}", customerId);
    }

    @Override
    public void deleteCustomerRequest(Integer customerId) {
        logger.info("Deleting customer with ID: {}", customerId);
        if (!customerRepository.existsById(customerId)) {
            logger.error("Error occurred while deleting customer: Customer with ID {} not found", customerId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, customerId);
        }
        customerRepository.deleteById(customerId);
        logger.info("Deleted customer with ID: {}", customerId);
    }

    private Customer getCustomerRequest(CustomerRequest customerRequest){
        logger.info("Getting customer from request");
        Customer customer = Customer.builder().firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .emailAddress(customerRequest.getEmailAddress())
                .address(customerRequest.getAddress()).build();
        logger.info("Got customer: {}", customer.getFirstName() + " " + customer.getLastName());
        return customer;
    }
}
