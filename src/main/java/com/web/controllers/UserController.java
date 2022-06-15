package com.web.controllers;

import com.dto.*;
import com.dto.AwardReceiveDto;
import com.exceptions.*;
import com.web.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private static final Logger userControllerLogger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCourses");
        return userService.getCurrentUserAccessibleCourses();
    }

    @GetMapping(value = "/user/coursesSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCoursesSortByCost");
        return userService.getCurrentUserAccessibleCoursesSortByCost();
    }

    @GetMapping(value = "/user/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleLessons");
        return userService.getCurrentUserAccessibleLessons();
    }

    @GetMapping(value = "/user/lessonsSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleLessonsSortByCost");
        return userService.getCurrentUserAccessibleLessonsSortByCost();
    }

    @PutMapping(value = "/user/subscribeCourse")
    @ResponseBody
    public boolean subscribeToCourse(@RequestParam String courseName) throws NotFoundException, NotAuthenticatedException, CourseExistException {
        userControllerLogger.info("start subscribeToCourse");
        return userService.subscribeToCourse(courseName);
    }

    @PutMapping(value = "/user/subscribeLesson")
    @ResponseBody
    public boolean subscribeToLesson(@RequestParam String lessonName) throws NotFoundException, NotAuthenticatedException {
        userControllerLogger.info("start subscribeToLesson");
        return userService.subscribeToLesson(lessonName);
    }

    @PostMapping(value = "/user/createCourse")
    @ResponseBody
    public boolean createCourse(@RequestBody CourseDtoReceive course) throws NotAuthenticatedException, InvalidDateException, CourseExistException {
        userControllerLogger.info("start createCourse");
        return userService.createCourse(course.getCourseName(), course.getCost(), course.getStartDate(), course.getEndDate());
    }

    //автоматом генерит и курс
    @PostMapping(value = "/user/createLesson")
    @ResponseBody
    public boolean createLesson(@RequestBody CreateLessonDto createLessonDto) throws NotAuthenticatedException, InvalidDateException, CourseExistException, LessonExistException {
        userControllerLogger.info("start createLesson");
        return userService.createLesson(createLessonDto.getCourseName(), createLessonDto.getLessonDescription(), createLessonDto.getCost(), createLessonDto.getStartDate(), createLessonDto.getEndDate());
    }

    @GetMapping(value = "/user/schedule")
    @ResponseBody
    public List<ScheduleLessonDto> getSchedule() throws NotAuthenticatedException, NotFoundException {
        userControllerLogger.info("start getSchedule");
        return userService.getSchedule();
    }

    @PutMapping(value = "/user/award")
    @ResponseBody
    public boolean assignAward(@RequestBody AwardReceiveDto awardReceiveDto) throws NotFoundException {
        userControllerLogger.info("start assignAward");
        return userService.assignAward(awardReceiveDto.getAward(), awardReceiveDto.getLogin());
    }


}
