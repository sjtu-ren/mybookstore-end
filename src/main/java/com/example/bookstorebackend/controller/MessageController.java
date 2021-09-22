package com.example.bookstorebackend.controller;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/addOrder")
    public JSONObject addOrder(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        Integer bookId=Integer.valueOf(params.getString("bookId"));
        List<Integer> ids= new ArrayList<Integer>(){{
            this.add(userId);
            this.add(bookId);
        }};
        System.out.println("send：");
        System.out.println(ids);
        jmsTemplate.convertAndSend("getOrder",new ArrayList<>(ids));
        JSONObject obj=new JSONObject();
        obj.put("msg","success");
        return obj;
    }
}
