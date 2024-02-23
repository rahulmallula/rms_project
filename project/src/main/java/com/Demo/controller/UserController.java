package com.Demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Demo.model.UserDetails;
import com.Demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new UserDetails());
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDetails());
        return "register";

    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDetails user, RedirectAttributes redirectAttributes) {
        UserDetails existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
        	redirectAttributes.addFlashAttribute("message", "Username already exists. Please choose a different username.");
            return "redirect:/register";
        } else {
            // Save the new user and redirect to register page
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please login.");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
    	return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserDetails userDetails, RedirectAttributes redirectAttributes) {
        UserDetails existingUser = userRepository.findByUsername(userDetails.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(userDetails.getPassword())) {
            // Redirect to dashboard or home page upon successful login
            return "redirect:/dashboard";
        } else {
            // Redirect back to login page with error message
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password. Please try again.");
            return "redirect:/login";
        }
    }

    // Additional methods for dashboard, profile, etc. can be added here
}

