package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.OrderDao;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public Order addOne(Integer userId,Integer bookId){
        return orderDao.addOne(userId, bookId);
    }

    @Override
    public JSONObject userGetAll(Integer userId){
        return orderDao.getAll(userId);
    }

    @Override
    public JSONObject admGetAll(){
        return orderDao.getALlOrders();
    }
}
