package com.example.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Main application view controller, serves static thymeleaf views found in the 
 * templates directory, post mapping function processRegister takes user input
 * data from the static form and persists it to a user database
 */

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @RequestMapping("/register")
    public String viewRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "signup_form";
    }

    @PostMapping(value = "/process_register")
    public String processRegister(User user) {
        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    
}
