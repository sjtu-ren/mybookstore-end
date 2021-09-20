package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisterController {
    @Autowired
    private UserService userService;


    @RequestMapping(value="/check",method = RequestMethod.POST)
    public JSONObject checkUsername(@RequestBody JSONObject params){
        String username=params.getString("username");
        User user=userService.findUser(username);
        JSONObject obj=new JSONObject();
        if(user!=null){
            obj.put("msg","用户名已存在");
        }
        else{
            obj.put("msg","用户名不存在");
        }
        return obj;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public JSONObject register(@RequestBody JSONObject params){
        String username=params.getString("username");
        String password=params.getString("password");
        String email=params.getString("email");
        JSONObject obj=new JSONObject();
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
