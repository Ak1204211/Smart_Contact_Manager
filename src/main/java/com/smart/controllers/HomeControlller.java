package com.smart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.config.Message;
import com.smart.dao.UserRepository;
import com.smart.entities.User;

//import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeControlller {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home - Smart Contact Manager");
		return "home";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","About - Smart Contact Manager");
		return "about";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register - Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String register( @Valid @ModelAttribute("user") User user,BindingResult result1   ,HttpSession session,Model model) {
		
		try {
			if(result1.hasErrors()) {
				model.addAttribute("user",user);
				return "signup";
			}
			
			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
//			System.out.println(user);
			User result = this.userRepository.save(user);
			model.addAttribute("user",new User());
			session.setAttribute("message",new Message("Successfully registered!", "alert-success"));
			System.out.println(result);
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(user);
			session.setAttribute("message",new Message("Something goes wrong!", "alert-danger"));
			return "signup";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title","Login - Smart Contact Manager");
		return "login";
	}

	@GetMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("title","Login - Smart Contact Manager");
		return "login";
	}
}
