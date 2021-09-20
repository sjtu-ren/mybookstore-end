package com.example.bookstorebackend.service;

import com.example.bookstorebackend.entity.User;

import java.util.List;

public interface UserService {
    User checkUser(String username,String password);
    User findUser(String username);
    User addUser(String username,String password,String email);

    List<User> getUsers();
    String banUser(Integer userId);
    String releaseUser(Integer userId);
}
