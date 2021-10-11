package com.example.springdemo.controller;

import com.example.springdemo.dto.CrmProjects;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.ProjectService;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registerProject")
public class ProjectRegistrationController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    String projectRegistrationForm = "new-project";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showProjectRegistrationForm")
    public String showProjectRegistrationForm(@RequestParam("userId") int theId, Model theModel) {

        theModel.addAttribute("crmProjects", new CrmProjects());

        theModel.addAttribute("userId", theId);

        return projectRegistrationForm;
    }

    @PostMapping("/processProjectRegistrationForm")
    public String processRegistrationForm(@RequestParam("userId") int userId,
            @Valid @ModelAttribute("crmProjects") CrmProjects theCrmProjects,
            BindingResult theBindingResult,
            Model theModel) {

        // form validation
        if (theBindingResult.hasErrors()) {
            return projectRegistrationForm;
        }

        // create user account

        logger.log(Level.INFO,"Successfully recieved user: {0}",userId);
        User user = userService.findById(userId);
        theCrmProjects.setUser(user);
        logger.log(Level.INFO,"Processing registration form for: {0}",user.getUserName());

        projectService.save(theCrmProjects);


        return "redirect:/userHome";

    }


    }
