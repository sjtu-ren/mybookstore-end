package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

import java.util.List;

public interface OrderDao {
    Order addOne(Integer userId, Integer bookId);
    JSONObject getAll(Integer userId);
    JSONObject getALlOrders();
}
