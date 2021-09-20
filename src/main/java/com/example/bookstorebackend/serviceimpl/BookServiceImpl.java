package com.example.bookstorebackend.serviceimpl;


import com.example.bookstorebackend.dao.BookDao;
import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getBooks(){
        return bookDao.getBooks();
    }
    @Override
    public Book getBook(Integer id){
        return bookDao.getBook(id);
    }
    @Override
    public Book getBook(String name){
        return bookDao.getBook(name);
    }
    @Override
    public Book findBook(String name){
        return bookDao.findBook(name);
    }
}
