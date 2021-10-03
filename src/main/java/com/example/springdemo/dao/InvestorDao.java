package com.example.springdemo.dao;

import com.example.springdemo.entity.Investor;



public interface InvestorDao {

    public Investor findByUserName(String userName);
    
    public void save(Investor investor);
    
}
