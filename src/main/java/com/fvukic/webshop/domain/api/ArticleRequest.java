package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.ArticleCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleRequest {

    @NotBlank
    private String name;

    @NotNull
    private Double articlePrice;

    private ArticleCategory articleCategory;
}
