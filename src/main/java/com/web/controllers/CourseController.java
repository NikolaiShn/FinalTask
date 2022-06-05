package com.web.controllers;

import com.dto.CourseDto;
import com.dto.mappers.CourseMapper;
import com.web.dao.CourseRepository;
import com.web.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }
}
