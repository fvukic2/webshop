package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.service.ArticleCategoryService;
import com.fvukic.webshop.util.Helper;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
    private void saveNewArticleCategoryRequest(@Valid @RequestBody ArticleCategoryRequest articleCategoryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleCategoryService.saveNewArticleCategoryRequest(articleCategoryRequest);
    }

    @PutMapping
    private void updateArticleCategoryRequest(@Valid @RequestBody ArticleCategoryRequest articleCategoryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleCategoryService.updateArticleCategoryRequest(articleCategoryRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticleCategoryRequest(@PathVariable Integer id){
        articleCategoryService.deleteArticleCategoryRequest(id);
    }
}
