package com.example.bookstorebackend.controller;


import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.service.OrderService;
import com.example.bookstorebackend.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdmController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/getUsers")
    public List<User> getUsers(@RequestBody JSONObject params){
        return userService.getUsers();
    }
    @RequestMapping("/banUser")
    public JSONObject banUser(@RequestBody JSONObject params){
        Integer userId=params.getInt("userId");
        JSONObject obj=new JSONObject();
        obj.put("msg",userService.banUser(userId));
        return obj;
    }
    @RequestMapping("/releaseUser")
    public JSONObject releaseUser(@RequestBody JSONObject params){
        Integer userId=params.getInt("userId");
        JSONObject obj=new JSONObject();
        obj.put("msg",userService.releaseUser(userId));
        return obj;
    }

    @RequestMapping("/admGetOrders")
    public JSONObject getOrders(@RequestBody JSONObject param){
        return orderService.admGetAll();
    }

    @RequestMapping("/admOrdByTime")
    public JSONObject admGetOrdersByTime(@RequestBody JSONObject params){
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
        return orderService.admGetAll(start,end);
    }
    @RequestMapping("/admCBook")
    public JSONObject admComBook(@RequestBody JSONObject params){
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
        return orderService.comBooks(start,end);
    }
    @RequestMapping("/admByUser")
    public JSONObject admByUser(@RequestBody JSONObject params){
        String username=params.getString("username");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=new Date();
        Date end=new Date();
        try {
            start=simpleDateFormat.parse(params.getString("start"));
        } catch (ParseException e) {
            e.printStackTrace();
            String x="2000-01-01 00:00:00";
            try {
                start=simpleDateFormat.parse(x);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        try {
            end=simpleDateFormat.parse(params.getString("end"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderService.comBooks(username,start,end);
    }
}
