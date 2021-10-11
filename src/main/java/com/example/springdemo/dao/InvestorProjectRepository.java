package com.example.springdemo.dao;

import com.example.springdemo.entity.InvestorProjects;
import com.example.springdemo.entity.ProjectPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorProjectRepository extends JpaRepository<InvestorProjects, ProjectPK> {
}
