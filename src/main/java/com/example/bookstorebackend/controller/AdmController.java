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
}
