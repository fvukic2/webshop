package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.repository.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoryServiceImplementation implements ArticleCategoryService {

    private ArticleCategoryRepository articleCategoryRepository;

    public ArticleCategoryServiceImplementation(ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    @Override
    public List<ArticleCategory> getAllArticleCategories() {
        return articleCategoryRepository.findAll();
    }

    @Override
    public void saveNewArticleCategory(ArticleCategory articleCategory) {
        articleCategoryRepository.save(articleCategory);
    }

    @Override
    public void deleteArticleCategory(Integer id) {
        articleCategoryRepository.deleteById(id);
    }

    @Override
    public void updateArticleCategory(ArticleCategory articleCategory) {
        articleCategoryRepository.save(articleCategory);
    }

    private ArticleCategory articleCategory(ArticleCategoryRequest articleCategoryRequest){
        return ArticleCategory.builder().name(articleCategoryRequest.getName()).build();
    }
}
