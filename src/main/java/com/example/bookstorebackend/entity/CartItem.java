package com.example.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "cart_item")
@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "cartItemId")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    @Column(name = "id",nullable = false)
    private Integer cartItemId;


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "item_number")
    private Integer item_number;

    @Column(name = "price")
    private Integer price;

}
