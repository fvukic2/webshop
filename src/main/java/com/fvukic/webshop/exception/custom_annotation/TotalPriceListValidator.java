package com.fvukic.webshop.exception.custom_annotation;

import com.fvukic.webshop.domain.entity.Article;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TotalPriceListValidator implements ConstraintValidator<TotalPriceListConstraint, List<Article>> {

    @Override
    public void initialize(TotalPriceListConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Article> articles, ConstraintValidatorContext context) {
        if (articles == null || articles.isEmpty()) {
            return false;
        }

        double totalPrice = articles.stream()
                .filter(article -> article.getArticlePrice() != null)
                .mapToDouble(Article::getArticlePrice)
                .sum();

        return totalPrice >= 50;
    }
}
