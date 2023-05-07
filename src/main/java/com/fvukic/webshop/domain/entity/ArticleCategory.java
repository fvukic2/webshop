package com.fvukic.webshop.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ArticleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_category_id")
    private Integer articleCategoryId;

    @Column(name = "name")
    private String name;
}
