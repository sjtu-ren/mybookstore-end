package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.Book;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    Book getBook(Integer id);

    Book findBook(String name);
    Book getBook(String name);

    /**
     * purchaseBook - 购买一本书够减少相应的库存
     * */
    Integer purchaseBook(Integer bookId,Integer num);
}
