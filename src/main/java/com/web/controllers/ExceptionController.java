package com.web.controllers;

import com.exceptions.UserExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleNotFoundException(UserExistException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }
}
