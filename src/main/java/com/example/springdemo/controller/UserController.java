package com.example.springdemo.controller;

import com.example.springdemo.dto.CrmProjects;
import com.example.springdemo.entity.Projects;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.ProjectService;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
@RequestMapping("/user")
public class UserController {



    private UserService userService;

    @Autowired
    private ProjectService projectService;

    private Logger logger = Logger.getLogger(getClass().getName());

    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/listUserProjects")
    public String listUserProjects(@RequestParam("userId") int userid,Model model){

        logger.log(Level.INFO,"listing projects for user id: {0}",userid);

        User user = userService.findById(userid);
        model.addAttribute("userProjects",user.getUserProjects());

        return "list-user-projects";
    }

    @GetMapping("/editUserProject")
    public String editUserProject(@RequestParam("projectId") int id, Model model){
        logger.log(Level.INFO,"project id: {0}",id);

        Projects projects = projectService.findById(id);



        CrmProjects crmProjects = new CrmProjects();
        crmProjects.setId(projects.getId());
        crmProjects.setUser(projects.getUser());
        crmProjects.setTitle(projects.getTitle());
        crmProjects.setDescription(projects.getDescription());
        crmProjects.setTechstack(projects.getTechstack());
        crmProjects.setInvestment(projects.getInvestment());
        crmProjects.setDuration(projects.getDuration());

        model.addAttribute("crmProjects",crmProjects);


        return "update-project";

    }

    @PostMapping("/saveProject")
    public String saveProject(@Valid @ModelAttribute("crmProjects") CrmProjects crmProjects,
                              BindingResult theBindingResult,
                              Model theModel){

        if (theBindingResult.hasErrors()) {
            return "update-project";
        }
        logger.log(Level.INFO,"saving project id: {0}",crmProjects.getId());
        projectService.save(crmProjects);


        return "redirect:/user/listUserProjects/?userId=" + crmProjects.getUser().getId();
    }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("projectId") int id){


        logger.log(Level.INFO,"deleting project id: {0}",id);
        int userId = projectService.findById(id).getUser().getId();
        projectService.deleteById(id);

        return "redirect:/user/listUserProjects/?userId=" + userId;
    }





}
