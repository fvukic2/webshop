package com.fvukic.webshop.article;

import com.fvukic.webshop.article.model.ArticleRequest;
import com.fvukic.webshop.article_category.ArticleCategory;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.exception.WrongNewEntityId;
import com.fvukic.webshop.article_category.ArticleCategoryRepository;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplementation implements ArticleService {

    private ArticleRepository articleRepository;

    private ArticleCategoryRepository articleCategoryRepository;

    public ArticleServiceImplementation(ArticleRepository articleRepository, ArticleCategoryRepository articleCategoryRepository) {
        this.articleRepository = articleRepository;
        this.articleCategoryRepository = articleCategoryRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void saveNewArticleRequest(ArticleRequest articleRequest) {
        Article article = getArticle(articleRequest);
        articleRepository.save(article);
    }

    @Override
    public void deleteArticleRequest(Integer articleId) {
        if (!articleRepository.existsById(articleId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID, articleId);
        }
        articleRepository.deleteById(articleId);
    }

    @Override
    public void updateArticleRequest(ArticleRequest articleRequest,Integer articleId) {
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
        }
    }

    private Article getArticle(ArticleRequest articleRequest){
        return Article.builder().name(articleRequest.getName()).
                articleCategory(articleRequest.getArticleCategory()).
                articlePrice(articleRequest.getArticlePrice())
                .build();
    }
}
