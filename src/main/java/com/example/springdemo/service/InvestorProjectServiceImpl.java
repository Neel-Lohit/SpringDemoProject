package com.example.springdemo.service;

import com.example.springdemo.dao.InvestorProjectRepository;
import com.example.springdemo.entity.InvestorProjects;
import com.example.springdemo.entity.ProjectPK;
import com.example.springdemo.exception.InvestorProjectNotFoundException;
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
    public InvestorProjects findById(ProjectPK projectPK) {
        Optional<InvestorProjects> result = investorProjectRepository.findById(projectPK);

        InvestorProjects investorProjects;
        if (result.isPresent()){
            investorProjects = result.get();
        }
        else {
            throw new InvestorProjectNotFoundException("Did not find InvestorProject id - " + projectPK);
        }
        return investorProjects;

    }

    @Override
    public void save(InvestorProjects investorProjects) {
        investorProjectRepository.save(investorProjects);

    }

    @Override
    public void deleteById(ProjectPK projectPK) {
        investorProjectRepository.deleteById(projectPK);

    }
}
