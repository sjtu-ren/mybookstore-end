package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.service.BookService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {
    @Autowired
    private BookService bookService;
    private static int counter=0;
    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestBody JSONObject params){
        synchronized (this){
            counter++;
            System.out.format("访问次数：%d",counter);
        }
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestBody JSONObject params){
        Integer id= Integer.valueOf(params.getString("bookId"));
        System.out.println("bookid: "+id);
        return bookService.getBook(id);
    }

    @RequestMapping("/nameGetBook")
    public Book nameGetBook(@RequestBody JSONObject params){
        String name= params.getString("name");
        return bookService.getBook(name);
    }
    @RequestMapping("/searchBook")
    public JSONObject searchBook(@RequestBody JSONObject params){
        String bookName=params.getString("name");
        Book book=bookService.findBook(bookName);
        System.out.println(bookName);
        JSONObject obj=new JSONObject();
        if(book!=null){
            obj.put("msg","success");
            obj.put("book",book);
        }
        else{
            obj.put("msg","fail");
        }
        return obj;
    }
}
