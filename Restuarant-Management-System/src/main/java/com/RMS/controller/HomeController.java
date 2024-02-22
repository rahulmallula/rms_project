package com.RMS.controller;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RMS.model.UserDtls;
import com.RMS.repository.UserRepository_INTERFACE;
import com.RMS.service.UserService;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository_INTERFACE userRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}
    
    @GetMapping("/login")
    public String profile(Principal p, Model m) {
		String email = p.getName(); 
		UserDtls user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";

       
    }

    
    @PostMapping("/createUser")
	public String saveUser(@ModelAttribute UserDtls user, HttpSession session, Model m) {
		 System.out.println(user);

		UserDtls u = userService.createUser(user);

		if (u != null) {
			// System.out.println("save sucess");
			session.setAttribute("msg", "Register successfully");

		} else {
			// System.out.println("error in server");
			session.setAttribute("msg", "Something wrong server");
		}
		return "Created Succesfully";

//        return "redirect:/register";
    }
}
