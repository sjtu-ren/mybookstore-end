package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.OrderDao;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private OrderDao orderDao;
    @Override
    @JmsListener(destination = "getOrder")//, containerFactory = "myFactory")
    public void receiveMessage(List<Integer> ids){
        System.out.println("receive");
        System.out.println(ids.get(0));
        System.out.println(ids.get(1));
        orderDao.addOne(ids.get(0),ids.get(1));
    }
}
