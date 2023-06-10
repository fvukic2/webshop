package com.fvukic.webshop.article_category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleCategoryRequest {

    @NotBlank
    private String name;
}
