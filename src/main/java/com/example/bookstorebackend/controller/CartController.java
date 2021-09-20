package com.example.bookstorebackend.controller;


import com.example.bookstorebackend.service.CartService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {
    @Autowired
    private CartService cartService;


    @RequestMapping("/addCart")
    public JSONObject addCart(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        Integer bookId= Integer.valueOf(params.getString("bookId"));
        JSONObject obj=new JSONObject();
        if(cartService.addCart(userId,bookId)!=null){
            obj.put("msg","success");
        }
        else{
            obj.put("msg","fail");
        }
        return obj;
    }

    @RequestMapping("/getCart")
    public JSONObject getCart(@RequestBody JSONObject params){
        Integer userId= Integer.valueOf(params.getString("userId"));
        return cartService.getCart(userId);
    }

    @RequestMapping("/deleteCart")
    public JSONObject deleteCart(@RequestBody JSONObject params){
        Integer cartId= Integer.valueOf(params.getString("cartId"));
        return cartService.deleteCart(cartId);
    }

    @RequestMapping("/cartBuyOne")
    public JSONObject buyCart(@RequestBody JSONObject params) {
        Integer cartId = Integer.valueOf(params.getString("cartId"));
        JSONObject obj = new JSONObject();
        if (cartService.buyOne(cartId) != null) {
            obj.put("msg", "success");
        } else {
            obj.put("msg", "fail");
        }
        return obj;
    }
    @RequestMapping("/cartBuyAll")
    public JSONObject buyCarts(@RequestBody JSONObject params) {
        List<Integer> cartIds= getCartIds(params);
        return cartService.buyCarts(cartIds);
    }
    @RequestMapping("/cartDeleteAll")
    public JSONObject deleteCarts(@RequestBody JSONObject params) {
        List<Integer> cartIds= getCartIds(params);
        return cartService.deleteCarts(cartIds);
    }

    private List<Integer> getCartIds(@RequestBody JSONObject params) {
        JSONArray s= params.getJSONArray("cartIds");
        List<Integer> cartIds=new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            cartIds.add(s.getInt(i));
        }
        System.out.println(cartIds);
        return cartIds;
    }
}
