package com.fvukic.webshop.article_category;

import com.fvukic.webshop.article_category.model.ArticleCategoryRequest;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> getAllArticleCategories();

    void saveNewArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest);

    void deleteArticleCategoryRequest(Integer articleCategoryId);

    void updateArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest,Integer articleCategoryId);
}
