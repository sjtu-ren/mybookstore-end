package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.Cart;
import com.example.bookstorebackend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> getAllByCart(Cart cart);
    CartItem getCartItemByCart(Cart cart);
}
