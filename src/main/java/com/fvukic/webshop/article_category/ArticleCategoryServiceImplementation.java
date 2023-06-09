package com.fvukic.webshop.article_category;

import com.fvukic.webshop.article_category.model.ArticleCategoryRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
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
        ArticleCategory articleCategory = getArticleCategory(articleCategoryRequest);
        articleCategoryRepository.save(articleCategory);
    }

    @Override
    public void deleteArticleCategoryRequest(Integer articleCategoryId) {
        if (!articleCategoryRepository.existsById(articleCategoryId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleCategoryId);
        }
        articleCategoryRepository.deleteById(articleCategoryId);
    }

    @Override
    public void updateArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest,Integer articleCategoryId) {
        ArticleCategory existingArticleCategory = articleCategoryRepository.findById(articleCategoryId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleCategoryId));
        existingArticleCategory.setName(articleCategoryRequest.getName());
        articleCategoryRepository.save(existingArticleCategory);
    }

    private ArticleCategory getArticleCategory(ArticleCategoryRequest articleCategoryRequest){
        return ArticleCategory.builder().name(articleCategoryRequest.getName()).build();
    }
}
