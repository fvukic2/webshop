package com.fvukic.webshop.order.model;

import com.fvukic.webshop.article.Article;
import com.fvukic.webshop.customer.Customer;
import com.fvukic.webshop.payment.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    @NotBlank
    private String description;

    //@TotalPriceListConstraint
    private List<Article> articles;

    private Customer customer;

    private Payment payment;
}
