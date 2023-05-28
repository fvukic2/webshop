package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.service.ArticleCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articlecategory")
public class ArticleCategoryController {

    private ArticleCategoryService articleCategoryService;

    public ArticleCategoryController(ArticleCategoryService articleCategoryService) {
        this.articleCategoryService = articleCategoryService;
    }

    @GetMapping
    private List<ArticleCategory> getAllArticleCategories(){
        return articleCategoryService.getAllArticleCategories();
    }

    @PostMapping
    private void saveNewArticleCategory(@RequestBody ArticleCategoryRequest articleCategoryRequest){
        articleCategoryService.saveNewArticleCategory(articleCategoryRequest);
    }

    @PutMapping
    private void updateArticleCategory(@RequestBody ArticleCategoryRequest articleCategoryRequest){
        articleCategoryService.updateArticleCategory(articleCategoryRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticleCategory(@PathVariable Integer id){
        articleCategoryService.deleteArticleCategory(id);
    }
}
