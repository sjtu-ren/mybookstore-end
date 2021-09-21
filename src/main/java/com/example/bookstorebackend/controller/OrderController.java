package com.example.bookstorebackend.controller;


import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public JSONObject addOrder(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        Integer bookId=Integer.valueOf(params.getString("bookId"));
        Order order=orderService.addOne(userId,bookId);
        JSONObject obj=new JSONObject();
        if(order!=null){
            obj.put("msg","success");
        }
        else{
            obj.put("msg","fail");
        }
        return obj;
    }

    @RequestMapping("/getOrders")
    public JSONObject getOrders(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        return orderService.userGetAll(userId);
    }
    @RequestMapping("/getOrdersByTime")
    public JSONObject getOrdersByTime(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=new Date();
        Date end=new Date();
        try {
            start=simpleDateFormat.parse(params.getString("start"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end=simpleDateFormat.parse(params.getString("end"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderService.userGetAll(userId,start,end);
    }
    @RequestMapping("/comByTime")
    public JSONObject comByTime(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=new Date();
        Date end=new Date();
        try {
            start=simpleDateFormat.parse(params.getString("start"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end=simpleDateFormat.parse(params.getString("end"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(start);
        return orderService.comAll(userId,start,end);
    }


}
