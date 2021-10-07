package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.entity.OrderItem;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface OrderItemDao {

    /**
     * addOne - 增加一个订单明细
     * */
    OrderItem addOne(Book book, Integer num, Integer totalPrice, Order order);
}
