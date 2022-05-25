package com.web.controllers;

import com.exceptions.UserExistException;
import com.model.User;
import com.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User admin(@RequestParam String login) {
        UserDetails userDetails = userService.loadUserByUsername(login);
        return new User(2L, userDetails.getUsername(), "sdsds", "lll");
    }

    @GetMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role) throws UserExistException {
        userService.registerUser(username, password, role);
        return "index";
    }
}
