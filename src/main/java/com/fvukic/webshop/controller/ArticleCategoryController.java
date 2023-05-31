package com.fvukic.webshop.controller;

import com.fvukic.webshop.domain.api.ArticleCategoryRequest;
import com.fvukic.webshop.domain.entity.ArticleCategory;
import com.fvukic.webshop.service.ArticleCategoryService;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new ArticleCategoryRequest to database", notes = "Enter all ArticleCategoryRequest parameters to add new ArticleCategoryRequest object to database", response = ArticleCategoryRequest.class)
    private void saveNewArticleCategoryRequest(@ApiParam(value = "ArticleCategoryRequest value you pass to the database")@Valid @RequestBody ArticleCategoryRequest articleCategoryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleCategoryService.saveNewArticleCategoryRequest(articleCategoryRequest);
    }

    @PutMapping
    @ApiOperation(value = "Updates ArticleCategoryRequest in database", notes = "Enter ArticleCategoryRequest id to update ArticleCategoryRequest object in database", response = ArticleCategoryRequest.class)
    private void updateArticleCategoryRequest(@ApiParam(value = "ArticleCategoryRequest value you pass to the database")@Valid @RequestBody ArticleCategoryRequest articleCategoryRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleCategoryService.updateArticleCategoryRequest(articleCategoryRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteArticleCategoryRequest(@PathVariable Integer id){
        articleCategoryService.deleteArticleCategoryRequest(id);
    }
}
