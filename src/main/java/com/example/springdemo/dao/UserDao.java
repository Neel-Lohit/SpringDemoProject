package com.example.springdemo.dao;

import com.example.springdemo.entity.User;


public interface UserDao {

    public User findByUserName(String userName);

    
}
