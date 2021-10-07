package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.OrderItemDao;
import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.entity.OrderItem;
import com.example.bookstorebackend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderItem addOne(Book book, Integer num, Integer totalPrice, Order order){
        OrderItem orderItem=new OrderItem();
        orderItem.setBook(book);
        orderItem.setItemNumber(num);
        orderItem.setTotal_price(totalPrice);
        orderItem.setMyOrder(order);
        return(orderItemRepository.save(orderItem));
    }
}
