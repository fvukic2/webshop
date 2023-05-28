package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void saveNewArticle(ArticleRequest articleRequest);

    void deleteArticle(Integer id);

    void updateArticle(ArticleRequest articleRequest);

}
