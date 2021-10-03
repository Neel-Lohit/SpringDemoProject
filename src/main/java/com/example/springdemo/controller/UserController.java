package com.example.springdemo.controller;

import com.example.springdemo.entity.User;
import com.example.springdemo.required.CrmUser;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/list")
    public String listUsers(Model model){

        List<User> users = userService.findAll();

        model.addAttribute("users",users);
        return "list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model)
    {
        User user = new User();

        model.addAttribute("user",user);

        return "user-form";
    }

    @GetMapping("/registerUser")
    public String registerUser(Model model)
    {
        User user = new User();

        model.addAttribute("user",user);

        return "user-registration-form";
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute("user") User user){

        userService.save(user);

        return "redirect:/user/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int id, Model model){

        User user = userService.findById(id);

        model.addAttribute("user",user);

        return "user-form";

    }

    @GetMapping("/editUserDetails")
    public String editUserDetails(@RequestParam("userId") int id, Model model){

        User user = userService.findById(id);

        model.addAttribute("user",user);

        return "user-registration-form";

    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id){

        userService.deleteById(id);

        return "redirect:/user/list";
    }

}
