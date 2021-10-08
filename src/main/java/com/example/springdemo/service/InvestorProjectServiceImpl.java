package com.example.springdemo.service;

import com.example.springdemo.dao.InvestorProjectRepository;
import com.example.springdemo.entity.InvestorProjects;
import com.example.springdemo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestorProjectServiceImpl implements InvestorProjectService{

    private InvestorProjectRepository investorProjectRepository;

    @Autowired
    public InvestorProjectServiceImpl(InvestorProjectRepository theInvestorProjectRepository)
    {
        investorProjectRepository = theInvestorProjectRepository;
    }


    @Override
    public List<InvestorProjects> findAll() {
        return investorProjectRepository.findAll();
    }

    @Override
    public InvestorProjects findById(int theId) {
        Optional<InvestorProjects> result = investorProjectRepository.findById(theId);

        InvestorProjects investorProjects;
        if (result.isPresent()){
            investorProjects = result.get();
        }
        else {
            throw new UserNotFoundException("Did not find InvestorProject id - " + theId);
        }
        return investorProjects;

    }

    @Override
    public void save(InvestorProjects investorProjects) {
        investorProjectRepository.save(investorProjects);

    }

    @Override
    public void deleteById(int theId) {
        investorProjectRepository.deleteById(theId);

    }
}
