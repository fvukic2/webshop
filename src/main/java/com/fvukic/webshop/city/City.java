package com.fvukic.webshop.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fvukic.webshop.address.Address;
import com.fvukic.webshop.country.Country;
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
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties({"address"})
@ApiModel(description = "Fields of the City entity")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private Integer cityId;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "city")
    private Address address;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;


}
