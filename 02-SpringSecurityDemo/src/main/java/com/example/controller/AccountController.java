package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

private double balance;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to ABC Bank!!!";
	}

	@GetMapping("/contact")
	public String contact() {
		return "Email: support@abcbank.com";
	}
	// RequestParam
	// http://localhost:8082/deposit?amount=23000
	
	@GetMapping("/deposit")
	public String deposit(@RequestParam("amount") double amount) {
		balance+=amount;
		return "Deposited Rs."+amount+"/- sucessfully! Available Balance in your Account is: "+balance;
	}
	
	@GetMapping("/withdraw")
	public String withdraw(@RequestParam("amount") double amount) {
		balance-=amount;
		return "Withdrawn Rs."+amount+"/- sucessfully! Available Balance in your Account is: "+balance;
	}
}
