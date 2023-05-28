package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> getAllArticleCategories();

    void saveNewArticleCategory(ArticleCategoryRequest articleCategoryRequest);

    void deleteArticleCategory(Integer id);

    void updateArticleCategory(ArticleCategoryRequest articleCategoryRequest);
}
