package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "from User where username =:username and password=:password")
    User checkUser(@Param("username")String username,@Param("password")String password);

    @Query(value = "from User where username =:username")
    User findUser(@Param("username")String username);

    List<User> getAllByType(Integer type);
}
