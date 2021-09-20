package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.CartDao;
import com.example.bookstorebackend.dao.OrderDao;
import com.example.bookstorebackend.entity.*;
import com.example.bookstorebackend.repository.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Cart addCart(Integer userId,Integer bookId){
        User user=userRepository.getOne(userId);
        Book book=bookRepository.getOne(bookId);
        Cart cart=new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        CartItem cartItem=new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        /*默认购买本数为1*/
        Integer number=1;
        Integer price=book.getPrice()*number;
        cartItem.setItem_number(number);
        cartItem.setPrice(price);
        cartItemRepository.save(cartItem);
        return cart;
    }

    @Override
    public JSONObject getCart(Integer userId){
        List<Cart> carts=cartRepository.getAllByUser_UserId(userId);
        List<CartItem> cartItems=new ArrayList<>();
        carts.forEach(cart -> {
            cartItems.addAll(cartItemRepository.getAllByCart(cart));
        });
        List<Book> books=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        List<Integer> prices=new ArrayList<>();
        cartItems.forEach(cartItem -> {
            books.add(cartItem.getBook());
            nums.add(cartItem.getItem_number());
            prices.add(cartItem.getPrice());
        });
        JSONObject obj=new JSONObject();
        obj.put("books",books);
        obj.put("nums",nums);
        obj.put("prices",prices);
        obj.put("cart",carts);
        return obj;
    }

    @Override
    public JSONObject deleteOne(Integer cartId){
        Cart cart=cartRepository.getOne(cartId);
        CartItem cartItem=cartItemRepository.getCartItemByCart(cart);
        cartItemRepository.delete(cartItem);
        cartRepository.delete(cart);
        JSONObject obj =new JSONObject();
        if(!cartRepository.findById(cartId).isPresent()){
            obj.put("msg","success");
        }
        else {
            obj.put("msg","fail");
        }
        return obj;
    }
    @Override
    public JSONObject deleteAll(List<Integer> cartIds){
        cartIds.forEach(this::deleteOne);
        return assertCarts(cartIds);
    }

    @Override
    public Order buyOne(Integer cartId){
        Cart cart=cartRepository.getOne(cartId);
        CartItem cartItem=cartItemRepository.getCartItemByCart(cart);
        Book book=cartItem.getBook();
        User user=cart.getUser();
        cartItemRepository.delete(cartItem);
        cartRepository.delete(cart);
        return orderDao.addOne(user.getUserId(),book.getBookId());
    }

    @Override
    public JSONObject buyAll(List<Integer> cartIds){
        for (Integer cartId : cartIds) {
            buyOne(cartId);
        }
        return assertCarts(cartIds);
    }

    private JSONObject assertCarts(List<Integer> cartIds) {
        JSONObject obj =new JSONObject();
        AtomicReference<Boolean> flag= new AtomicReference<>(true);
        cartIds.forEach(integer -> {
            if(cartRepository.findById(integer).isPresent()){
                flag.set(false);
            }
        });
        if(flag.get()){
            obj.put("msg","success");
        }
        else{
            obj.put("msg","fail");
        }
        return obj;
    }
}
