package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getAllByUser(User user);
}
