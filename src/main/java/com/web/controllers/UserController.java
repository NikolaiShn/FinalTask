package com.web.controllers;

import com.dto.CourseDto;
import com.dto.LessonDto;
import com.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCourses() {
        return userService.getCurrentUserAccessibleCourses();
    }

    @GetMapping(value = "/user/coursesSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() {
        return userService.getCurrentUserAccessibleCoursesSortByCost();
    }

    @GetMapping(value = "/user/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessons() {
        return userService.getCurrentUserAccessibleLessons();
    }

    @GetMapping(value = "/user/lessonsSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() {
        return userService.getCurrentUserAccessibleLessonsSortByCost();
    }
}
