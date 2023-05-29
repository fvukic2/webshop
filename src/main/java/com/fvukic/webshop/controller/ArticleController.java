package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.ArticleRequest;
import com.fvukic.webshop.domain.entity.Article;
import com.fvukic.webshop.service.ArticleService;
import com.fvukic.webshop.util.Helper;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
    private void saveNewArticleRequest(@Valid @RequestBody ArticleRequest articleRequest,  BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleService.saveNewArticleRequest(articleRequest);
    }

    @PutMapping
    private void updateArticleRequest(@Valid @RequestBody ArticleRequest articleRequest,  BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleService.updateArticleRequest(articleRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticleRequest(@PathVariable Integer id){
        articleService.deleteArticleRequest(id);
    }

}
