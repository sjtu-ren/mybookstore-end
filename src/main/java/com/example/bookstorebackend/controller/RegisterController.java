package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@Scope("session")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisterController {
    @Autowired
    WebApplicationContext webApplicationContext;

    @RequestMapping(value="/check",method = RequestMethod.POST)
    public JSONObject checkUsername(@RequestBody JSONObject params){
        String username=params.getString("username");
        UserService userService=webApplicationContext.getBean(UserService.class);
        System.out.println("检查用户名");
        System.out.println(userService);
        System.out.println(this);
        User user=userService.findUser(username);
        JSONObject obj=new JSONObject();
        if(user!=null){
            obj.put("msg","exist");
        }
        else{
            obj.put("msg","success");
        }
        return obj;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public JSONObject register(@RequestBody JSONObject params){
        String username=params.getString("username");
        String password=params.getString("password");
        String email=params.getString("email");
        JSONObject obj=new JSONObject();
        UserService userService=webApplicationContext.getBean(UserService.class);
        System.out.println("注册用户");
        System.out.println(userService);
        System.out.println(this);
        if(userService.findUser(username)!=null){
            obj.put("msg","用户名已存在");
        }
        else{
            User user=userService.addUser(username,password,email);
            if(user!=null){
                obj.put("msg","注册成功");
            }
            else{
                obj.put("msg","注册失败");
            }
        }
        return obj;
    }
}
