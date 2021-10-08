package com.example.springdemo.service;

import com.example.springdemo.entity.InvestorProjects;


import java.util.List;

public interface InvestorProjectService {

    List<InvestorProjects> findAll();

    InvestorProjects findById(int theId);

    void save(InvestorProjects investorProjects);

    void deleteById(int theId);
}
