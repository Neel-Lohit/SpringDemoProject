package com.example.springdemo.service;

import com.example.springdemo.entity.User;
import com.example.springdemo.dto.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> findAll();

    public User findById(int theId);

    public void save(User user);

    public void deleteById(int theId);

    public User findByUserName(String userName);

    public void save(CrmUser crmUser);




}
