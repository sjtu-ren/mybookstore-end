package com.example.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "cart")
@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "cartId")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    @Column(name = "id",nullable = false)
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
