package com.fvukic.webshop.article;

import com.fvukic.webshop.article.model.ArticleRequest;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    void saveNewArticleRequest(ArticleRequest articleRequest);

    void deleteArticleRequest(Integer articleId);

    void updateArticleRequest(ArticleRequest articleRequest,Integer articleId);

}
