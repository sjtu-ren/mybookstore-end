package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.BookDao;
import com.example.bookstorebackend.dao.OrderDao;
import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.entity.Order;
import com.example.bookstorebackend.entity.OrderItem;
import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.repository.BookRepository;
import com.example.bookstorebackend.repository.OrderItemRepository;
import com.example.bookstorebackend.repository.OrderRepository;
import com.example.bookstorebackend.repository.UserRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookDao bookDao;

    @Override
    public Order addOne(Integer userId, Integer bookId){
        Order order=new Order();
        User user=userRepository.getOne(userId);
        System.out.println("user:"+user);
        Book book=bookRepository.getOne(bookId);
        System.out.println("Book:"+book);
        OrderItem orderItem=new OrderItem();
        Integer price=book.getPrice();
        /*默认每次添加订单书籍一本*/
        Integer itemNum=1;
        Integer totalPrice=price*itemNum;
        /*更新书库书本数量*/
        book.setNumber(book.getNumber()-itemNum);
        order.setUser(user);
        Date time=new Date();
        order.setTime(time);
        orderRepository.save(order);
        orderItem.setBook(book);
        orderItem.setItemNumber(itemNum);
        orderItem.setTotal_price(totalPrice);
        orderItem.setMyOrder(order);
        orderItemRepository.save(orderItem);
        orderItem=orderItemRepository.getOne(orderItem.getOrderItemId());
        System.out.println("orderItemid:"+orderItem.getOrderItemId());
        return order;
    }

    @Override
    public JSONObject getAll(Integer userId){
        User user=userRepository.getOne(userId);
        List<Order> orders=orderRepository.getAllByUser(user);
        List<OrderItem> orderItems= new ArrayList<>();
        List<String> dates=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.forEach(order -> {
            orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
            dates.add(fmt.format(order.getTime()));
        });
        List<Book> books= new ArrayList<>();
        return createOrderList(orderItems, dates, nums, books);
    }
    @Override
    public JSONObject getALlOrders(){
        List<Order> orders=orderRepository.findAll();
        List<OrderItem> orderItems=new ArrayList<>();
        List<String> dates=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        List<String> usernames=new ArrayList<>();
        List<Book> books = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.forEach(order -> {
            orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
            dates.add(fmt.format(order.getTime()));
            usernames.add(order.getUser().getUsername());
        });
        JSONObject obj=createOrderList(orderItems, dates, nums, books);
        obj.put("users",usernames);
        return obj;
    }
    @Override
    public JSONObject getAll(Integer userId,Date start,Date end){
        User user=userRepository.getOne(userId);
        List<Order> orders=orderRepository.getAllByUser(user);
        List<OrderItem> orderItems= new ArrayList<>();
        List<String> dates=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.forEach(order -> {
            if(order.getTime().after(start)&&order.getTime().before(end)){
                orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
                dates.add(fmt.format(order.getTime()));
            }
        });
        List<Book> books= new ArrayList<>();
        return createOrderList(orderItems, dates, nums, books);
    }
    @Override
    public JSONObject getAllOrders(Date start,Date end){
        List<Order> orders=orderRepository.findAll();
        List<OrderItem> orderItems= new ArrayList<>();
        List<String> dates=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        List<String> usernames=new ArrayList<>();
        List<Book> books = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.forEach(order -> {
            if(order.getTime().after(start)&&order.getTime().before(end)){
                orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
                dates.add(fmt.format(order.getTime()));
                usernames.add(order.getUser().getUsername());
            }
        });
        JSONObject obj=createOrderList(orderItems, dates, nums, books);
        obj.put("users",usernames);
        return obj;
    }
    private JSONObject createOrderList(List<OrderItem> orderItems, List<String> dates, List<Integer> nums, List<Book> books) {
        orderItems.forEach(orderItem -> {
            books.add(orderItem.getBook());
            nums.add(orderItem.getItemNumber());
        });
        JSONObject obj=new JSONObject();
        obj.put("time",dates);
        obj.put("num",nums);
        obj.put("books",books);
        return obj;
    }

    @Override
    public JSONObject comOrders(Integer userId,Date start,Date end){
        User user=userRepository.getOne(userId);
        List<Order> orders=orderRepository.getAllByUser(user);
        List<OrderItem> orderItems= new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        orders.forEach(order -> {
            if(order.getTime().after(start)&&order.getTime().before(end)){
                orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
            }
        });
        List<Book> books= new ArrayList<>();
        List<Integer> prices=new ArrayList<>();
        orderItems.forEach(orderItem -> {
            Book tmpBook=orderItem.getBook();
            Integer tmpNum=orderItem.getItemNumber();
            Integer price=tmpBook.getPrice();
            if(books.contains(tmpBook)){
                int index=books.indexOf(tmpBook);
                Integer num1=nums.get(index);
                nums.set(index,num1+tmpNum);
                prices.set(index,price*(num1+tmpNum));
            }
            else{
                books.add(tmpBook);
                nums.add(tmpNum);
                prices.add(price*tmpNum);
            }
        });
        JSONObject obj=new JSONObject();
        obj.put("books",books);
        obj.put("num",nums);
        obj.put("price",prices);
        return obj;
    }

    @Override
    public JSONObject comBooks(Date start,Date end){
        List<Order> orders=orderRepository.findAll();
        List<OrderItem> orderItems= new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Integer> prices=new ArrayList<>();
        orders.forEach(order -> {
            if(order.getTime().after(start)&&order.getTime().before(end)){
                orderItems.add(orderItemRepository.getOrderItemByMyOrder_OrderId(order.getOrderId()));
            }
        });
        orderItems.forEach(orderItem -> {
            Book tmpBook=orderItem.getBook();
            Integer tmpNum=orderItem.getItemNumber();
            Integer price=tmpBook.getPrice();
            if(books.contains(tmpBook)){
                int index=books.indexOf(tmpBook);
                Integer num1=nums.get(index);
                nums.set(index,num1+tmpNum);
                prices.set(index,price*(num1+tmpNum));
            }
            else{
                books.add(tmpBook);
                nums.add(tmpNum);
                prices.add(price*tmpNum);
            }
        });
        JSONObject obj=new JSONObject();
        obj.put("books",books);
        obj.put("num",nums);
        obj.put("price",prices);
        return obj;
    }
}
