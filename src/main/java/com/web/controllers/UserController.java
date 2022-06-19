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

    /**
     * Метод возврщающий все курсы данного пользователя.
     */
    @GetMapping(value = "/user/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCourses");
        return userService.getCurrentUserAccessibleCourses();
    }

    /**
     * Метод возврщающий все курсы данного пользователя отсортированные по стоимости(возврастанию).
     */
    @GetMapping(value = "/user/coursesSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCoursesSortByCost");
        return userService.getCurrentUserAccessibleCoursesSortByCost();
    }

    /**
     * Метод возврщающий все занятия данного пользователя.
     */
    @GetMapping(value = "/user/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleLessons");
        return userService.getCurrentUserAccessibleLessons();
    }

    /**
     * Метод возврщающий все занятия данного пользователя отсортированные по стоимости(возврастанию).
     */
    @GetMapping(value = "/user/lessonsSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleLessonsSortByCost");
        return userService.getCurrentUserAccessibleLessonsSortByCost();
    }

    /**
     * Метод для подписки пользователя на курс. Доступен аутентифицированным пользователям.
     * @param courseName - имя курса на который планирует подписаться юзер
     */
    @PutMapping(value = "/user/subscribeCourse")
    @ResponseBody
    public boolean subscribeToCourse(@RequestParam String courseName) throws NotFoundException, NotAuthenticatedException, CourseExistException {
        userControllerLogger.info("start subscribeToCourse");
        return userService.subscribeToCourse(courseName);
    }

    /**
     * Метод для подписки пользователя на занятие. Доступен аутентифицированным пользователям.
     * Автоматиески подписывает на все занятия в курсе к которому относится занятие
     * @param lessonDescription - описание занятия на которое планирует подписаться юзер
     */
    @PutMapping(value = "/user/subscribeLesson")
    @ResponseBody
    public boolean subscribeToLesson(@RequestParam String lessonDescription) throws NotFoundException, NotAuthenticatedException {
        userControllerLogger.info("start subscribeToLesson");
        return userService.subscribeToLesson(lessonDescription);
    }

    /**
     * Метод для создания курса пользователем. Доступен аутентифицированным пользователям.
     * Автоматиески подписывает на курс пользователя.
     * @param course - описание курса который планирует создать юзер
     *               Пример описания json:
     *               {
     *                  "courseName":"courseName",
     *                  "cost":"55",
     *                  "startDate":"2022-06-08 12:00:00",
     *                  "endDate":"2022-06-09 12:00:00"
     *               }
     */
    @PostMapping(value = "/user/createCourse")
    @ResponseBody
    public boolean createCourse(@RequestBody CourseDtoReceive course) throws NotAuthenticatedException, InvalidDateException, CourseExistException {
        userControllerLogger.info("start createCourse");
        return userService.createCourse(course.getCourseName(), course.getCost(), course.getStartDate(), course.getEndDate());
    }

    /**
     * Метод для создания занятия пользователем. Доступен аутентифицированным пользователям.
     * Автоматически генерирует курс.
     * Имя курса будет соответствовать имени занятия, в занятии пустое время.
     * Форма занятия по дефолту индивидуальное.
     * @param createLessonDto - описание занятия которое планирует создать юзер
     *               Пример описания json:
     *               {
     *                  "lessonName":"lessonName",
     *                  "courseName":"courseName",
     *                  "lessonDescription":"lessonDescription",
     *                  "cost":"55",
     *                  "startDate":"2022-06-08 12:00:00",
     *                  "endDate":"2022-06-09 12:00:00"
     *               }
     */
    @PostMapping(value = "/user/createLesson")
    @ResponseBody
    public boolean createLesson(@RequestBody CreateLessonDto createLessonDto) throws NotAuthenticatedException, InvalidDateException, CourseExistException, LessonExistException {
        userControllerLogger.info("start createLesson");
        return userService.createLesson(createLessonDto.getLessonName(), createLessonDto.getCourseName(), createLessonDto.getLessonDescription(), createLessonDto.getCost(), createLessonDto.getStartDate(), createLessonDto.getEndDate());
    }

    /**
     * Метод для просмотра расписания пользователя. Доступен аутентифицированным пользователям.
     */
    @GetMapping(value = "/user/schedule")
    @ResponseBody
    public List<ScheduleLessonDto> getSchedule() throws NotAuthenticatedException, NotFoundException {
        userControllerLogger.info("start getSchedule");
        return userService.getSchedule();
    }

    /**
     * Метод для назначения награды преподаателю. Доступен только пользователям с ролью USER.
     * Награду возможно добавить только педагогу(тоесть роль ADMIN)
     * @param awardReceiveDto - описание награды
     *               Пример описания json:
     *               {
     *                  "login":"user",
     *                  "award":"60",
     *               }
     */
    @PutMapping(value = "/user/award")
    @ResponseBody
    public boolean assignAward(@RequestBody AwardReceiveDto awardReceiveDto) throws NotFoundException, IncorrectAssignAward {
        userControllerLogger.info("start assignAward");
        return userService.assignAward(awardReceiveDto.getAward(), awardReceiveDto.getLogin());
    }
}
