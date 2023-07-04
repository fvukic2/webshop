package com.fvukic.webshop.article;

import com.fvukic.webshop.article.model.ArticleRequest;
import com.fvukic.webshop.article_category.ArticleCategory;
import com.fvukic.webshop.article_category.ArticleCategoryRepository;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.exception.WrongNewEntityId;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplementation implements ArticleService {

    private ArticleRepository articleRepository;

    private ArticleCategoryRepository articleCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImplementation.class);

    public ArticleServiceImplementation(ArticleRepository articleRepository, ArticleCategoryRepository articleCategoryRepository) {
        this.articleRepository = articleRepository;
        this.articleCategoryRepository = articleCategoryRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        logger.info("Fetching all articles");
        List<Article> articles = articleRepository.findAll();
        logger.info("Fetched {} articles", articles.size());
        return articles;
    }

    @Override
    public void saveNewArticleRequest(ArticleRequest articleRequest) {
        logger.info("Saving new article request");
        Article article = getArticle(articleRequest);
        articleRepository.save(article);
        logger.info("Saved new article: {}", article.getName());
    }

    @Override
    public void deleteArticleRequest(Integer articleId) {
        logger.info("Deleting article with ID: {}", articleId);
        if (!articleRepository.existsById(articleId)){
            logger.error("Error occurred while deleting article: Article with ID {} not found", articleId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, articleId);
        }
        articleRepository.deleteById(articleId);
        logger.info("Deleted article with ID: {}", articleId);
    }

    @Override
    public void updateArticleRequest(ArticleRequest articleRequest,Integer articleId) {
        logger.info("Updating article with ID: {}", articleId);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,articleId));
        if (article != null) {
            if (articleRequest.getArticleCategory() != null) {
                ArticleCategory newCategory = articleCategoryRepository.findById(articleRequest.getArticleCategory().getArticleCategoryId())
                        .orElseThrow(() -> new WrongNewEntityId(ErrorResponse.ERROR_WRONG_NEW_ID));
                article.setArticleCategory(newCategory);
            }
            if (articleRequest.getName() != null) {
                article.setName(articleRequest.getName());
            }
            if (articleRequest.getArticlePrice() != null) {
                article.setArticlePrice(articleRequest.getArticlePrice());
            }
            articleRepository.save(article);
            logger.info("Updated article with ID: {}", articleId);
        }
    }

    private Article getArticle(ArticleRequest articleRequest){
        logger.info("Getting article from request");
        Article article = Article.builder().name(articleRequest.getName()).
                articleCategory(articleRequest.getArticleCategory()).
                articlePrice(articleRequest.getArticlePrice())
                .build();
        logger.info("Got article: {}", article.getName());
        return article;
    }
}
