package com.web.controllers;

import com.exceptions.RoleNotExistException;
import com.exceptions.UserExistException;
import com.web.services.UserAuthenticationService;
import com.web.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private static final Logger registrationControllerLogger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @GetMapping(value = "/")
    public String mainPage() {
        registrationControllerLogger.info("start mainPage");
        registrationControllerLogger.info("end mainPage");
        return "index";
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        registrationControllerLogger.info("start registrationPage");
        registrationControllerLogger.info("end registrationPage");
        return "registration";
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role, @ModelAttribute("name") String name, @ModelAttribute("lastName") String lastName) throws UserExistException, RoleNotExistException {
        registrationControllerLogger.info("start registration");
        userService.registerUser(username, password, role, name, lastName);
        registrationControllerLogger.info("end registration");
        return "index";
    }
}
