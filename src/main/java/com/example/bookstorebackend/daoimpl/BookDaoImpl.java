package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.BookDao;
import com.example.bookstorebackend.entity.Book;
import com.example.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    @Override
    public Book getBook(Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getBook(String name){
        return bookRepository.findBookByBookName(name);
    }
    @Override
    public Book findBook(String name){
        String bookName="%"+name+"%";
        return bookRepository.findBookByBookNameContains(name);
    }
}
