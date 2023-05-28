package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImplementation implements ArticleService{

    private ArticleRepository articleRepository;

    public ArticleServiceImplementation(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void saveNewArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    private Article getArticle(ArticleRequest articleRequest){
        return Article.builder().name(articleRequest.getName()).
                articleCategory(articleRequest.getArticleCategory()).build();
    }
}
