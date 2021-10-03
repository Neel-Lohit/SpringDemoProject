package com.example.springdemo.controller;

import com.example.springdemo.entity.Investor;
import com.example.springdemo.entity.User;
import com.example.springdemo.required.CrmUser;
import com.example.springdemo.service.InvestorService;
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
@RequestMapping("/registerInvestor")
public class InvestorRegistrationController {
	
    @Autowired
    private InvestorService investorService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showInvestorRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		return "investor-registration-form";
	}

	@PostMapping("/processInvestorRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		logger.log(Level.INFO,"Processing registration form for: {0}",userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "investor-registration-form";
	        }

		// check the database if user already exists
        Investor existing = investorService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "investor-registration-form";
        }
        
        // create user account        						
        investorService.save(theCrmUser);
        
        logger.log(Level.INFO,"Successfully created investor: {0}",userName);
        
        return "user-registration-confirmation";
	}
}
