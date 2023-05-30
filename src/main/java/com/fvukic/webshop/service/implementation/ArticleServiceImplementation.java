package com.fvukic.webshop.service.implementation;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.repository.ArticleRepository;
import com.fvukic.webshop.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplementation implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImplementation(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
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
    public void updateArticleRequest(ArticleRequest articleRequest) {
        Article article = getArticle(articleRequest);
        articleRepository.save(article);
    }

    private Article getArticle(ArticleRequest articleRequest){
        return Article.builder().name(articleRequest.getName()).
                articleCategory(articleRequest.getArticleCategory()).
                articlePrice(articleRequest.getArticlePrice())
                .build();
    }
}
