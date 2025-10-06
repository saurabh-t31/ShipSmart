package com.ShipSmart.ShipSmart.controller;

import com.ShipSmart.ShipSmart.entity.Role;
import com.ShipSmart.ShipSmart.entity.User;
import com.ShipSmart.ShipSmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user.getName(), user.getEmail(), user.getPassword(), user.getRole());
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
