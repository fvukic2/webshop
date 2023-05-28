package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private String description;

    private Double totalPrice;

    private List<Article> articles;
}
