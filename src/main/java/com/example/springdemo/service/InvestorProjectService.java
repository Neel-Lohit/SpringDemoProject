package com.example.springdemo.service;

import com.example.springdemo.entity.InvestorProjects;
import com.example.springdemo.entity.ProjectPK;


import java.util.List;

public interface InvestorProjectService {

    List<InvestorProjects> findAll();

    InvestorProjects findById(ProjectPK projectPK);

    void save(InvestorProjects investorProjects);

    void deleteById(ProjectPK projectPK);
}
