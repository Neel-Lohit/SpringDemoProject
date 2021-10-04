package com.example.springdemo.service;

import com.example.springdemo.entity.User;
import com.example.springdemo.dto.CrmUser;


import java.util.List;

public interface UserService{

    List<User> findAll();

    User findById(int theId);

    void save(User user);

    void deleteById(int theId);

    User findByUserName(String userName);

    void save(CrmUser crmUser);




}
