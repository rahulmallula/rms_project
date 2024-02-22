package com.RMS.controller;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.RMS.model.UserDtls;
import com.RMS.repository.UserRepository_INTERFACE;
import com.RMS.service.UserService;



@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository_INTERFACE userRepo;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		String email = p.getName();
		UserDtls user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/user/home")
	public String home() {
		return "home";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute UserDtls user, Model m) {

		 System.out.println("Saveuser");

		UserDtls u = userService.createUser(user);
		System.out.println(user);

		if (u != null) {
			 System.out.println("save sucess");
//			session.setAttribute("msg", "Register successfully");

		} else {
			 System.out.println("error in server");
//			session.setAttribute("msg", "Something wrong server");
		}
		return "redirect:/register";
	}

}
