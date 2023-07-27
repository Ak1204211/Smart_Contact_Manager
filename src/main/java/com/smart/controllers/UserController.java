package com.smart.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.config.Message;
import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@ModelAttribute
	public User addCommonData(Model model,Principal principal) {
		String email = principal.getName();
		User user = userRepository.getUserByUserName(email);
		model.addAttribute(user);
		return user;
	}

	@GetMapping("/index")
	public String dashboard(Model model) {
		
		model.addAttribute("title","Dashboard - Smart Contact Manager");
		return "normal/user_dashboard";
	}
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title","Add Contact - Smart Contact Manager");
		model.addAttribute("contact",new Contact());
		return "normal/add_contact_form";
	}

	@PostMapping("/process-contact")
	public String formProcess(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file,Model model,Principal principal,HttpSession session) {
		
		try {
		User user=this.addCommonData(model, principal);
		
		if(file.isEmpty()) {
			System.out.println("File is empty");
			contact.setImage("contact.png");
		}
		else {
			contact.setImage(file.getOriginalFilename());
			File savedFile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(savedFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image is uploaded");
		}
		contact.setUser(user);
		user.getContacts().add(contact);
		this.userRepository.save(user);
		System.out.println(contact);
		Message message = new Message("Successfully added to your Contact List !", "success");
		session.setAttribute("message", message);
		}
		catch(Exception e) {
			System.out.println("ERROR : "+e.getMessage());
			e.printStackTrace();
			Message message = new Message("Something went wrong !", "danger");
			session.setAttribute("message", message);
		}
		return "normal/add_contact_form";
	}

	@GetMapping("/view-contacts/{page}")
	public String showContacts(@PathVariable("page")int page,Model model,Principal principal) {
		model.addAttribute("title","View Contacts - Smart Contact Manager");
		User user = addCommonData(model, principal);
		Pageable pageable = PageRequest.of(page,5);
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
		model.addAttribute("contacts",contacts);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPage",contacts.getTotalPages());
		return "normal/view_contacts";
	}

}
