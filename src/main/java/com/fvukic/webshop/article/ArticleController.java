package com.fvukic.webshop.article;

import com.fvukic.webshop.article.model.ArticleRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new ArticleRequest to database", notes = "Enter all ArticleRequest parameters to add new ArticleRequest object to database", response = ArticleRequest.class)
    private void saveNewArticleRequest(@ApiParam(value = "ArticleRequest value you pass to the database")@Valid @RequestBody ArticleRequest articleRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleService.saveNewArticleRequest(articleRequest);
    }

    @PutMapping("/{articleId}")
    @ApiOperation(value = "Updates ArticleRequest in database", notes = "Enter ArticleRequest id to update ArticleRequest object in database", response = ArticleRequest.class)
    private void updateArticleRequest(@ApiParam(value = "ArticleRequest value you pass to the database")@Valid @PathVariable Integer articleId, @RequestBody ArticleRequest articleRequest,  BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        articleService.updateArticleRequest(articleRequest,articleId);
    }

    @DeleteMapping("/{articleId}")
    private void deleteArticleRequest(@PathVariable Integer articleId){
        articleService.deleteArticleRequest(articleId);
    }

}
