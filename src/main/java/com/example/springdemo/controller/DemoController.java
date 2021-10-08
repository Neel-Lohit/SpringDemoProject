package com.example.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/userHome")
	public String showUserHome() {
		
		return "userHome";
	}


	@GetMapping("/investorHome")
	public String showInvestorHome() {

		return "investorHome";
	}

}










