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
    /**
     * Метод возврщающий главную страницу.
     */
    @GetMapping(value = "/")
    public String mainPage() {
        registrationControllerLogger.info("start mainPage");
        registrationControllerLogger.info("end mainPage");
        return "index";
    }
    /**
     * Метод возвращающий страницу регистрации.
     */
    @GetMapping(value = "/registration")
    public String registrationPage() {
        registrationControllerLogger.info("start registrationPage");
        registrationControllerLogger.info("end registrationPage");
        return "registration";
    }
    /**
     * Метод для регистрации пользователей.
     * @param username - логин пользователя
     * @param password - пароль пользователя
     * @param role - название роли(бывает 2-х типов ADMIN, USER)
     * @param name - имя пользователя
     * @param lastName - фамилия пользователя
     * @throws UserExistException - если такой пользователь уже существует
     * @throws RoleNotExistException - если указан не верный тип роли
     */
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("role") String role, @ModelAttribute("name") String name, @ModelAttribute("lastName") String lastName) throws UserExistException, RoleNotExistException {
        registrationControllerLogger.info("start registration");
        userService.registerUser(username, password, role, name, lastName);
        registrationControllerLogger.info("end registration");
        return "index";
    }
}
