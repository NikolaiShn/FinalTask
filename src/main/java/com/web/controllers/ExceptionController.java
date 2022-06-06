package com.web.controllers;

import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
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

    @ExceptionHandler(InvalidDateException.class)
    public ModelAndView handleInvalidDateException(InvalidDateException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(IncorrectInputException.class)
    public ModelAndView handleIncorrectInputException(IncorrectInputException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

}
