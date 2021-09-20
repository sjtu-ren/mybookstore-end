package com.example.bookstorebackend.dao;


import com.example.bookstorebackend.entity.Cart;
import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

import java.util.List;

public interface CartDao {
    Cart addCart(Integer userId, Integer bookId);

    JSONObject getCart(Integer userId);

    JSONObject deleteOne(Integer cartId);

    Order buyOne(Integer cartId);

    JSONObject deleteAll(List<Integer> cartIds);

    JSONObject buyAll(List<Integer> cartIds);

}
