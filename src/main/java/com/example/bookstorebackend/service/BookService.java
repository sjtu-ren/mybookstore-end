package com.example.bookstorebackend.service;

import com.example.bookstorebackend.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBook(Integer id);
    Book getBook(String name);
    Book findBook(String name);
}
