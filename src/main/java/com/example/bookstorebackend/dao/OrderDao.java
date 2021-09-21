package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.Order;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    Order addOne(Integer userId, Integer bookId);
    /*
    * getAll - 用户获得全部订单
    * */
    JSONObject getAll(Integer userId);
    /*
    *getAll - 根据时间筛选用户的全部订单
    * */
    JSONObject getAll(Integer userId,Date start,Date end);
    /**
     * getAllOrders - 管理员获得全部订单
     * */
    JSONObject getALlOrders();
    /*
     * getAllOrders - 管理员根据时间筛选全部订单
     * */
    JSONObject getAllOrders(Date start,Date end);
    /*
     * comOrders - 用户根据时间统计购买书籍的数量
     * */
    JSONObject comOrders(Integer userId,Date start,Date end);
    /*
     * comBooks - 管理员根据时间统计书籍销量
     * */
    JSONObject comBooks(Date start,Date end);
}
