package com.fvukic.webshop.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "order_article",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private List<Article> articles;

}
