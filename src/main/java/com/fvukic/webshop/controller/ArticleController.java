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
    private void saveNewArticle(@RequestBody ArticleRequest articleRequest){
        articleService.saveNewArticle(articleRequest);
    }

    @PutMapping
    private void updateArticle(@RequestBody ArticleRequest articleRequest){
        articleService.updateArticle(articleRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticle(@PathVariable Integer id){
        articleService.deleteArticle(id);
    }

}
