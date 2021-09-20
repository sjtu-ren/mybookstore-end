package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.UserDao;
import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username,String password){
        return userDao.checkUser(username,password);
    }

    @Override
    public  User findUser(String username){
        return userDao.findUser(username);
    }
    @Override
    public User addUser(String username,String password,String email){
        return userDao.addUser(username,password,email);
    }
    @Override
    public List<User> getUsers(){
        return userDao.getUsers();
    }
    @Override
    public String banUser(Integer userId){
        return userDao.banUser(userId);
    }
    @Override
    public String releaseUser(Integer userId){
        return userDao.releaseUser(userId);
    }
}
