package com.example.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderItemId")
public class OrderItem {
    @Id
    @Column(name = "order_item_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Integer orderItemId;

    @Column(name = "item_number")
    private Integer itemNumber;

    @Column(name = "total_price")
    private Integer total_price;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order myOrder;

    @JoinColumn(name = "book_id")
    @ManyToOne
    private Book book;




}
