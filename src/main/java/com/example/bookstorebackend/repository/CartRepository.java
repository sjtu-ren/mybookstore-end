package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> getAllByUser_UserId(Integer userId);
}
