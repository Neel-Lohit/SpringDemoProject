package com.example.springdemo.dao;

import com.example.springdemo.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Integer> {

}
