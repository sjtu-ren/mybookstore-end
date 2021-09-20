package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();

    Book findBookByBookNameContains(String name);

    Book findBookByBookName(String name);
}
