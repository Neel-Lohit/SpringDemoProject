package com.example.springdemo.service;

import com.example.springdemo.dao.ProjectRepository;
import com.example.springdemo.dto.CrmProjects;
import com.example.springdemo.entity.Projects;
import com.example.springdemo.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProjectServiceImpl implements ProjectService{


    private ProjectRepository projectRepository;

    private Logger logger = Logger.getLogger(getClass().getName());



    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }




    @Override
    public List<Projects> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Projects findById(int theId) {
        Optional<Projects> result = projectRepository.findById(theId);

        Projects projects;
        if (result.isPresent()){
            projects = result.get();
        }
        else {
            throw new ProjectNotFoundException("Did not find Project id - " + theId);
        }
        return projects;
    }

    @Override
    public void save(Projects projects) {
        projectRepository.save(projects);

    }

    @Override
    public void deleteById(int theId) {
        projectRepository.deleteById(theId);

    }

    @Override
    public void save(CrmProjects crmProjects) {
        Projects projects = new Projects();

        if (crmProjects.getId()!=0)
        {
            projects.setId(crmProjects.getId());
            logger.log(Level.INFO,"Successfully got id: {0}",crmProjects.getId());
        }
        projects.setTitle(crmProjects.getTitle());
        projects.setDescription(crmProjects.getDescription());
        projects.setTechstack(crmProjects.getTechstack());
        projects.setInvestment(crmProjects.getInvestment());
        projects.setDuration(crmProjects.getDuration());
        projects.setUser(crmProjects.getUser());




        projectRepository.save(projects);

    }
}
