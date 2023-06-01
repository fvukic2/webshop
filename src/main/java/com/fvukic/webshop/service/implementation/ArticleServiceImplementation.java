package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.repository.ArticleCategoryRepository;
import com.fvukic.webshop.repository.ArticleRepository;
import com.fvukic.webshop.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public void deleteArticleRequest(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticleRequest(ArticleRequest articleRequest,Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));
        if (article != null) {
            if (articleRequest.getArticleCategory() != null) {
                ArticleCategory newCategory = articleCategoryRepository.findById(articleRequest.getArticleCategory().getArticleCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("New ArticleCategory with given ID does not exist"));
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
