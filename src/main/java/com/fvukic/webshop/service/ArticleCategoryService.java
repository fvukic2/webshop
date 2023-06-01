package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> getAllArticleCategories();

    void saveNewArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest);

    void deleteArticleCategoryRequest(Integer id);

    void updateArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest,Integer id);
}
