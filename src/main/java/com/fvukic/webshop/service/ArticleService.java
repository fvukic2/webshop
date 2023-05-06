package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void saveNewArticle(Article article);

    void deleteArticle(Integer id);

    void updateArticle(Article article);

}
