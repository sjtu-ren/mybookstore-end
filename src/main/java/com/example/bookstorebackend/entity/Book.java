package com.example.bookstorebackend.entity;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "book")
@Proxy(lazy = false)
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Integer bookId;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "name")
    private String bookName;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String bookType;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String bookImage;

    @Column(name = "number")
    private Integer number;


    public Integer getBookId() {
        return bookId;
    }

    public Integer getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public String getDescription() {
        return description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}


