package com.kodnest.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kodnest.app.entities.User;
import com.kodnest.app.service.UserService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/user/api")
public class UserController {
 
	UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String getSignUp() {
		return "signup";
	}
	
	@GetMapping("/signup")
	public String signUpImpl(@RequestParam("username") String uname, @RequestParam("password") String upassword, @RequestParam("email") String uemail, @RequestParam("role") String urole) {
		User newUser = new User(uname, upassword, uemail, urole);
		boolean success = userService.userSignUp(newUser);
		if(success) {
			return "login";
		}
		else {
			return "invalidsignup";
		}
	}
	
	@GetMapping("/login")
	public String userLogin(@RequestParam String username, @RequestParam String password) {
		boolean result = userService.userSignIn(username, password);
		if(result) {
			return "verify";
		}
		else {
			return "loginfailed";
		}
	}
	
	@GetMapping("/verify")
	public String verifyOtp(@RequestParam("otpvalue") int otp, org.springframework.ui.Model model) {
		
	User ref =	userService.verifyOtp(otp);
		if(ref!=null) {
			model.addAttribute("username",ref.getUsername());
			return "home";
		}
		else
			return "login";
	}
	
	
}
