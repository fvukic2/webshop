package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.ArticleCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleRequest {

    @NotBlank
    private String name;

    private ArticleCategory articleCategory;
}
