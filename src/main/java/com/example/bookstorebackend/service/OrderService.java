package com.example.bookstorebackend.service;


import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

import java.util.Date;

public interface OrderService {
    Order addOne(Integer userId,Integer BookId);

    JSONObject userGetAll(Integer userId);
    JSONObject admGetAll();
    JSONObject userGetAll(Integer userId, Date start, Date end);
    JSONObject admGetAll(Date start, Date end);

    JSONObject comAll(Integer userId,Date start, Date end);

    JSONObject comBooks(Date start,Date end);
    JSONObject comBooks(String username,Date start,Date end);
}
