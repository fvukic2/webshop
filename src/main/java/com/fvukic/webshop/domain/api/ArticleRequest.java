package com.fvukic.webshop.domain.api;

import com.fvukic.webshop.domain.entity.ArticleCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleRequest {

    private String name;

    private ArticleCategory articleCategory;
}
