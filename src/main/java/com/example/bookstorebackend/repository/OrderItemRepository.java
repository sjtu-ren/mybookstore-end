package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    OrderItem getOrderItemByMyOrder_OrderId(Integer orderId);
}
