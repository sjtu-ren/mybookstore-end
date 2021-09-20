package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.CartDao;
import com.example.bookstorebackend.entity.Cart;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.service.CartService;
import net.sf.json.JSONObject;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;

    @Override
    public Cart addCart(Integer userId,Integer bookId){
        return cartDao.addCart(userId, bookId);
    }

    @Override
    public JSONObject getCart(Integer userId){
        return cartDao.getCart(userId);
    }

    @Override
    public JSONObject deleteCart(Integer cartId){
        return cartDao.deleteOne(cartId);
    }

    @Override
    public Order buyOne(Integer cartId){
        return cartDao.buyOne(cartId);
    }
    @Override
    public JSONObject buyCarts(List<Integer> cartIds){
        return cartDao.buyAll(cartIds);
    }
    @Override
    public JSONObject deleteCarts(List<Integer> cartIds){
        return cartDao.deleteAll(cartIds);
    }
}
