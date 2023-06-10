package com.fvukic.webshop.address;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fvukic.webshop.city.City;
import com.fvukic.webshop.customer.Customer;
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
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(description = "Fields of the Address entity")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
@JsonIgnoreProperties({"customer"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
