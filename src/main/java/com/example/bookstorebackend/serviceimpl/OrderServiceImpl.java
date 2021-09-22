package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.OrderDao;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Scope("singleton")
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
    @Override
    public JSONObject userGetAll(Integer userId, Date start, Date end){return orderDao.getAll(userId,start,end);}
    @Override
    public JSONObject admGetAll(Date start, Date end){return orderDao.getAllOrders(start,end);}

    @Override
    public JSONObject comAll(Integer userId,Date start, Date end){return orderDao.comOrders(userId,start,end);}
    @Override
    public JSONObject comBooks(Date start,Date end){return orderDao.comBooks(start,end);}

    @Override
    public JSONObject comBooks(String username,Date start,Date end){return orderDao.comBooks(username, start, end);}


}
