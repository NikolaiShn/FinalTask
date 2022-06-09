package com.web.controllers;

import com.exceptions.NotFoundException;
import com.exceptions.UserExistException;
import com.model.User;
import com.web.services.UserAuthenticationService;
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

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User admin(@RequestParam String login) throws NotFoundException {
        UserDetails userDetails = userAuthenticationService.loadUserByUsername(login);
        return new User();
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
    public String registration(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role, @ModelAttribute("name") String name, @ModelAttribute("lastName") String lastName) throws UserExistException {
        userService.registerUser(username, password, role, name, lastName);
        return "index";
    }
}
