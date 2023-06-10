package com.fvukic.webshop.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(description = "Fields of the Article entity")
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

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
