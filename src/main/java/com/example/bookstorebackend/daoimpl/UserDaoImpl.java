package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.UserDao;
import com.example.bookstorebackend.entity.User;
import com.example.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username,String password){
        return userRepository.checkUser(username,password);
    }

    @Override
    public User addUser(String username,String password,String email){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User findUser(String username){
        return userRepository.findUser(username);
    }

    @Override
    public List<User> getUsers(){
        List<User> users=userRepository.findAll();
        users.removeAll(userRepository.getAllByType(1));
        return users;
    }
    @Override
    public String banUser(Integer userId){
        User user=userRepository.getOne(userId);
        user.setActive(0);
        userRepository.save(user);
        if(user.getActive()==0){
            return "success";
        }
        else {
            return "fail";
        }
    }
    @Override
    public String releaseUser(Integer userId){
        User user=userRepository.getOne(userId);
        user.setActive(1);
        userRepository.save(user);
        if(user.getActive()==1){
            return "success";
        }
        else {
            return "fail";
        }
    }
}
