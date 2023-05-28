package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    private List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @PostMapping
    private void saveNewArticleRequest(@RequestBody ArticleRequest articleRequest){
        articleService.saveNewArticleRequest(articleRequest);
    }

    @PutMapping
    private void updateArticleRequest(@RequestBody ArticleRequest articleRequest){
        articleService.updateArticleRequest(articleRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticleRequest(@PathVariable Integer id){
        articleService.deleteArticleRequest(id);
    }

}
