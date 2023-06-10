package com.fvukic.webshop.article_category;

import com.fvukic.webshop.article_category.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
}
