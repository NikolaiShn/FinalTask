package com.web.controllers;

import com.dto.CourseDto;
import com.dto.CourseDtoReceive;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.web.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/coursesCostGreater/{cost}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getAllCourses(@PathVariable("cost") Double cost) {
        return courseService.findByCostGreaterThan(cost);
    }

    @GetMapping(value = "/coursesSortByStartDate")
    @ResponseBody
    public List<CourseDto> getAllCoursesAllOrderByStartDateDesc() {
        List<CourseDto> courseDtos = courseService.getAllCoursesAllOrderByStartDateDesc();
        return courseService.getAllCoursesAllOrderByStartDateDesc();
    }

    @GetMapping(value = "/coursesSortByEndDate")
    @ResponseBody
    public List<CourseDto> getAllCoursesAllOrderByEndDateDesc() {
        List<CourseDto> courseDtos = courseService.getAllCoursesAllOrderByStartDateDesc();
        return courseService.getAllCoursesAllOrderByEndDateDesc();
    }

    @PostMapping(value = "/course/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean create(@RequestBody CourseDtoReceive course) throws InvalidDateException {
        return courseService.createCourse(course.getCourseName(), course.getCost(), course.getStartDate(), course.getEndDate());
    }

    @PutMapping(value = "/course/editName")
    @ResponseBody
    public boolean editCourseName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName) throws NotFoundException {
        return courseService.editCourseName(oldName, newName);
    }

    @PutMapping(value = "/course/editCost")
    @ResponseBody
    public boolean editCourseName(@RequestParam("courseName") String courseName, @RequestParam("newCost") Double newCost) throws NotFoundException, IncorrectInputException {
        return courseService.editCourseCost(courseName, newCost);
    }

    @DeleteMapping(value = "/course/delete")
    @ResponseBody
    public boolean deleteCourse(@RequestParam("courseName") String courseName) throws NotFoundException {
        return courseService.deleteCourse(courseName);
    }

}
