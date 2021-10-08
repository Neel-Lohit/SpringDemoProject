package com.example.springdemo.service;

import com.example.springdemo.dto.CrmProjects;
import com.example.springdemo.entity.Projects;

import java.util.List;

public interface ProjectService {
    List<Projects> findAll();

    Projects findById(int theId);

    void save(Projects projects);

    void deleteById(int theId);

    void save(CrmProjects crmProjects);


}
