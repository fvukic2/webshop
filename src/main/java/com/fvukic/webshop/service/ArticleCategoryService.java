package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> getAllArticleCategories();

    void saveNewArticleCategory(ArticleCategory articleCategory);

    void deleteArticleCategory(Integer id);

    void updateArticleCategory(ArticleCategory articleCategory);
}
