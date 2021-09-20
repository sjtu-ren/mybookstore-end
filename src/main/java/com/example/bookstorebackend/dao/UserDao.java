package com.example.bookstorebackend.dao;

import com.example.bookstorebackend.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username,String password);
    User addUser(String username,String password,String email);
    User findUser(String username);

    List<User> getUsers();
    String banUser(Integer userId);
    String releaseUser(Integer userId);
}
