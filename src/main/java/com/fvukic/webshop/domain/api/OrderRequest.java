package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.domain.entity.Customer;
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

//    @NotNull
//    private Double totalPrice;

    private List<Article> articles;

    private Customer customer;
}
