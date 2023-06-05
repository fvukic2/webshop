package com.fvukic.webshop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(description = "Fields of the Order entity")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
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

    @Column(name = "discounted_price")
    private Double discountedPrice;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "order_article",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private List<Article> articles;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

}
