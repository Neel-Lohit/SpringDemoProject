package com.example.springdemo.service;

import com.example.springdemo.entity.Investor;
import com.example.springdemo.dto.CrmUser;


import java.util.List;

public interface InvestorService {

    List<Investor> findAll();

    Investor findById(int theId);

    void save(Investor investor);

    void deleteById(int theId);

    Investor findByUserName(String userName);

    void save(CrmUser crmUser);




}
