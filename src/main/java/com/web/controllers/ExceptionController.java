package com.web.controllers;

import com.exceptions.*;
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

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ModelAndView handleNotAuthenticatedException(NotAuthenticatedException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(CourseExistException.class)
    public ModelAndView handleCourseExistException(CourseExistException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(LessonExistException.class)
    public ModelAndView handleLessonExistException(LessonExistException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionClass", e.getClass());
        modelAndView.addObject("errMsg", e.getMessage());
        return modelAndView;
    }
}
