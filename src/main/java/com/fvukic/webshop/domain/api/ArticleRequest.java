package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.ArticleCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
