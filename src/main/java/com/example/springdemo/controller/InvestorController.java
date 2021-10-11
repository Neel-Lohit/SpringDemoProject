package com.example.springdemo.controller;


import com.example.springdemo.entity.*;
import com.example.springdemo.service.InvestorProjectService;
import com.example.springdemo.service.InvestorService;
import com.example.springdemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/investor")
public class InvestorController {

    private InvestorProjectService investorProjectService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvestorService investorService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public InvestorController(InvestorProjectService theInvestorProjectService) {
        investorProjectService = theInvestorProjectService;
    }

    @GetMapping("/listProjects")
    public String listProjects(@RequestParam("investorId") int investorId,
                                       Model model){

        logger.log(Level.INFO,"listing public projects for investor id: {0}",investorId);

        List<Projects> projects = projectService.findAll();
        List<InvestorProjects> investorProjects = investorProjectService.findAll();

        for (InvestorProjects invProj:investorProjects){
            Projects proj = projectService.findById(invProj.getKey().getProjectId());
            projects.remove(proj);
        }




        model.addAttribute("Projects",projects);
       model.addAttribute("investorId",investorId);

        return "list-projects";
    }

    @GetMapping("/addInvestorProject")
    public String addInvestorProject(@RequestParam("investorId") int investorId,
                                     @RequestParam("projectId") int projectId,
                                     Model model){

        logger.log(Level.INFO,"investor of investor id: {0}{0}",investorId);
        logger.log(Level.INFO,"invested in project of project id: {0}",projectId);

        Investor investor = investorService.findById(investorId);

        Projects project = projectService.findById(projectId);
       InvestorProjects investorProjects = new InvestorProjects();
       investorProjects.setInvestor(investor);
        ProjectPK projectPK = new ProjectPK();
        projectPK.setProjectId(projectId);
       investorProjects.setKey(projectPK);
       investorProjects.setProjects(project);

       investorProjectService.save(investorProjects);

        return "redirect:/investor/listProjects?investorId=" + investorId;
    }

    @GetMapping("/listInvestorProjects")
    public String listInvestorProjects(@RequestParam("investorId") int investorId,
                                       Model model){

        logger.log(Level.INFO,"listing projects of investor id: {0}",investorId);

        Investor investor = investorService.findById(investorId);


        List<Projects> projects = new ArrayList<>();
        for (InvestorProjects inv: investor.getInvestorProjects()){

            projects.add(projectService.findById(inv.getKey().getProjectId()));

        }


        model.addAttribute("investorProjects",projects);

        return "list-investor-projects";
    }

    @GetMapping("/deInvest")
    public String deleteProject(@RequestParam("projectId") int id){


        int investorId = investorProjectService.findById(new ProjectPK(id)).getInvestor().getId();
        investorProjectService.deleteById(new ProjectPK(id));

        logger.log(Level.INFO,"deleting project of project id: {0}",id);
        logger.log(Level.INFO,"for investor id: {0}",investorId);
        return "redirect:/investor/listInvestorProjects/?investorId=" + investorId;
    }



}
