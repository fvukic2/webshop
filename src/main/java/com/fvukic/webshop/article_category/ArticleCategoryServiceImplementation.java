package com.fvukic.webshop.article_category;

import com.fvukic.webshop.article_category.model.ArticleCategoryRequest;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoryServiceImplementation implements ArticleCategoryService {

    private ArticleCategoryRepository articleCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(ArticleCategoryServiceImplementation.class);

    public ArticleCategoryServiceImplementation(ArticleCategoryRepository articleCategoryRepository) {
        this.articleCategoryRepository = articleCategoryRepository;
    }

    @Override
    public List<ArticleCategory> getAllArticleCategories() {
        logger.info("Fetching all article categories");
        List<ArticleCategory> categories = articleCategoryRepository.findAll();
        logger.info("Fetched {} article categories", categories.size());
        return categories;
    }

    @Override
    public void saveNewArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest) {
        logger.info("Saving new article category request");
        ArticleCategory articleCategory = getArticleCategory(articleCategoryRequest);
        articleCategoryRepository.save(articleCategory);
        logger.info("Saved new article category: {}", articleCategory.getName());
    }

    @Override
    public void deleteArticleCategoryRequest(Integer articleCategoryId) {
        logger.info("Deleting article category with ID: {}", articleCategoryId);
        if (!articleCategoryRepository.existsById(articleCategoryId)){
            logger.error("Error occurred while deleting article category: Category with ID {} not found", articleCategoryId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleCategoryId);
        }
        articleCategoryRepository.deleteById(articleCategoryId);
        logger.info("Deleted article category with ID: {}", articleCategoryId);
    }

    @Override
    public void updateArticleCategoryRequest(ArticleCategoryRequest articleCategoryRequest,Integer articleCategoryId) {
        logger.info("Updating article category with ID: {}", articleCategoryId);
        ArticleCategory existingArticleCategory = articleCategoryRepository.findById(articleCategoryId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleCategoryId));
        existingArticleCategory.setName(articleCategoryRequest.getName());
        articleCategoryRepository.save(existingArticleCategory);
        logger.info("Updated article category with ID: {}", articleCategoryId);
    }

    private ArticleCategory getArticleCategory(ArticleCategoryRequest articleCategoryRequest){
        logger.info("Getting article category from request");
        ArticleCategory category = ArticleCategory.builder().name(articleCategoryRequest.getName()).build();
        logger.info("Got article category: {}", category.getName());
        return category;
    }
}
