package com.example.springdemo.service;

import com.example.springdemo.entity.Investor;
import com.example.springdemo.dto.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface InvestorService extends UserDetailsService {

    public List<Investor> findAll();

    public Investor findById(int theId);

    public void save(Investor investor);

    public void deleteById(int theId);

    public Investor findByUserName(String userName);

    public void save(CrmUser crmUser);




}
