package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    Book getBook(Integer id);

    Book findBook(String name);
    Book getBook(String name);
}
