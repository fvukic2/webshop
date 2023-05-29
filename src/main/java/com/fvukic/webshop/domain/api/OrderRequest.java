package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    @NotBlank
    private String description;

    @NotNull
    private Double totalPrice;

    private List<Article> articles;
}
