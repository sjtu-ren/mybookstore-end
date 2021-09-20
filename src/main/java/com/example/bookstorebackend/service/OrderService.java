package com.example.bookstorebackend.service;


import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

public interface OrderService {
    Order addOne(Integer userId,Integer BookId);

    JSONObject userGetAll(Integer userId);
    JSONObject admGetAll();
}
