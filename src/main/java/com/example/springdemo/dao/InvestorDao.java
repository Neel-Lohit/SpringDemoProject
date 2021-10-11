package com.example.springdemo.dao;

import com.example.springdemo.entity.Investor;



public interface InvestorDao {

    Investor findByUserName(String userName);
    

    
}
