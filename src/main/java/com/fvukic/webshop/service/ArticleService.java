package com.fvukic.webshop.service;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void saveNewArticleRequest(ArticleRequest articleRequest);

    void deleteArticleRequest(Integer id);

    void updateArticleRequest(ArticleRequest articleRequest,Integer articleId);

}
