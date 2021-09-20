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
}
