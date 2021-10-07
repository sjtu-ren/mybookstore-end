package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.BookDao;
import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.repository.BookRepository;
import com.example.bookstorebackend.utils.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    RedisUtil redisUtil;
    @Override
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    @Override
    public Book getBook(Integer id){return bookRepository.findById(id).orElse(null);}

    @Override
    public Book getBook(String name){
        Book book=null;
        System.out.println("searching book"+name+"in Redis.");
        Object obj=redisUtil.get("book"+name);
        if(obj==null){
            System.out.println("Book"+name+"is not in Redis");
            System.out.println("Searching book"+name+"in DB");
            book=bookRepository.findBookByBookName(name);
            redisUtil.set("book"+name, JSONArray.toJSON(book));
        }
        else{
            book=JSONArray.parseObject(obj.toString(),Book.class);
            System.out.println("Book"+name+"is in Redis");
        }
        return book;

    }
    @Override
    public Book findBook(String name){
        return bookRepository.findBookByBookNameContains(name);
    }
    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer purchaseBook(Integer bookId,Integer num){
        Book book=bookRepository.getOne(bookId);
        System.out.println("Book:"+book);
        Integer price=book.getPrice();
        try {
            book.setNumber(book.getNumber()-num);
            Object obj=redisUtil.get("book"+book.getBookName());
            bookRepository.save(book);
            if(obj!=null){
                System.out.println("Save"+book.getBookName()+"in Redis");
                redisUtil.del("book"+book.getBookName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return price*num;
    }
}
