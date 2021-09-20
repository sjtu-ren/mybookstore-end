package com.example.bookstorebackend.service;

import com.example.bookstorebackend.entity.Cart;
import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

import java.util.List;

public interface CartService {
    Cart addCart(Integer userId,Integer bookId);
    JSONObject getCart(Integer userId);
    JSONObject deleteCart(Integer cartId);
    Order buyOne(Integer cartId);
    JSONObject buyCarts(List<Integer> cartIds);
    JSONObject deleteCarts(List<Integer> cartIds);

}
