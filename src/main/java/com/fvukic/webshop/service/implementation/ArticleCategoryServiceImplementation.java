package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.repository.ArticleCategoryRepository;
import com.fvukic.webshop.service.ArticleCategoryService;
import com.fvukic.webshop.util.ErrorResponse;
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
    public void saveNewArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest) {
        ArticleCategory articleCategory = getarticleCategory(articleCategoryRequest);
        articleCategoryRepository.save(articleCategory);
    }

    @Override
    public void deleteArticleCategoryRequest(Integer id) {
        articleCategoryRepository.deleteById(id);
    }

    @Override
    public void updateArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest,Integer articleCategoryId) {
        ArticleCategory existingArticleCategory = articleCategoryRepository.findById(articleCategoryId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleCategoryId));
        existingArticleCategory.setName(articleCategoryRequest.getName());
        articleCategoryRepository.save(existingArticleCategory);
    }

    private ArticleCategory getarticleCategory(ArticleCategoryRequest articleCategoryRequest){
        return ArticleCategory.builder().name(articleCategoryRequest.getName()).build();
    }
}
