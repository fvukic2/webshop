package com.fvukic.webshop.payment.model;

import com.fvukic.webshop.customer.Customer;
import com.fvukic.webshop.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {

    private Double amount;

    private Order order;

    private Customer customer;

}
