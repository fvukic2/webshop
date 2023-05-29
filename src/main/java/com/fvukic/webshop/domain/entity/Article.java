package com.fvukic.webshop.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "name")
    private String name;

    @Column(name = "article_price")
    private Double articlePrice;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_category")
    private ArticleCategory articleCategory;

}
