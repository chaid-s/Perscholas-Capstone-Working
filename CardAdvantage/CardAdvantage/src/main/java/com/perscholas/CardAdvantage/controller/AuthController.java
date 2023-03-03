package com.perscholas.CardAdvantage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.perscholas.CardAdvantage.dto.RegistrationDto;
import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) 
	{
		this.userService = userService;
	}
	//handler for login request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	//handler for reg request
	@GetMapping("/register")
	public String showRegistrationForm(Model model){
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	//handler for submitting reg form
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user,
			BindingResult result, 
			Model model) {
		User existingUser = userService.findByEmail(user.getEmail());
		
		if(existingUser!=null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "An account with that email has already been registered.");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.saveUser(user);
		return "redirect:/register?success";
		
	}
	

}
