package com.fvukic.webshop.payment.model;

import com.fvukic.webshop.customer.Customer;
import com.fvukic.webshop.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {

    @NotNull
    private Double amount;

    private Order order;

    private Customer customer;

}
