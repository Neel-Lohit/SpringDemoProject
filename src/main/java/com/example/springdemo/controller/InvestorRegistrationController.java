package com.example.springdemo.controller;

import com.example.springdemo.entity.Investor;
import com.example.springdemo.dto.CrmUser;
import com.example.springdemo.entity.User;
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

	@Autowired
	private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	String investorRegistrationForm = "investor-registration-form";
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showInvestorRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		return investorRegistrationForm;
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
			 return investorRegistrationForm;
	        }

		// check the database if user already exists
		User existingUser = userService.findByUserName(userName);
		Investor existingInvestor = investorService.findByUserName(userName);
        if (existingUser != null || existingInvestor != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return investorRegistrationForm;
        }
        
        // create user account        						
        investorService.save(theCrmUser);
        
        logger.log(Level.INFO,"Successfully created investor: {0}",userName);
        
        return "user-registration-confirmation";
	}
}
