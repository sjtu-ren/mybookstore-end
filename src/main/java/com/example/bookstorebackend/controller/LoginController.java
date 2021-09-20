package com.example.bookstorebackend.controller;


import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject params){
        String username=params.getString("username");
        String password=params.getString("password");
        boolean remember= Boolean.parseBoolean(params.getString("checked"));
        User user=userService.checkUser(username,password);
        JSONObject obj=new JSONObject();
        System.out.println(username);
        System.out.println(params);
        System.out.println(password);
        if(user!=null){
            if(user.getActive()==1){
                obj.put("userId",user.getUserId());
                obj.put("username",user.getUsername());
                obj.put("type",user.getType());
                obj.put("active",user.getActive());
                if(remember){
                    obj.put("pwd",user.getPassword());
                }
                obj.put("msg","登录成功");
            }
            else {
                obj.put("msg","您的账户已被禁用!");
            }
        }
        else {
            obj.put("msg","用户名或密码错误");
        }
        return obj;
    }


}
