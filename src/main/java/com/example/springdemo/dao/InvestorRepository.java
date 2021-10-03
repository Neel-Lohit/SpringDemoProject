package com.example.springdemo.dao;

import com.example.springdemo.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Integer> {

}
