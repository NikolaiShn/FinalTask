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
     * Метод возвращающий все курсы данного пользователя.
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     */
    @GetMapping(value = "/user/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCourses");
        return userService.getCurrentUserAccessibleCourses();
    }

    /**
     * Метод возвращающий все курсы данного пользователя отсортированные по стоимости(возврастанию).
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     */
    @GetMapping(value = "/user/coursesSortByCost", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleCoursesSortByCost");
        return userService.getCurrentUserAccessibleCoursesSortByCost();
    }

    /**
     * Метод возвращающий все занятия данного пользователя.
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     */
    @GetMapping(value = "/user/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        userControllerLogger.info("start getCurrentUserAccessibleLessons");
        return userService.getCurrentUserAccessibleLessons();
    }

    /**
     * Метод возвращающий все занятия данного пользователя отсортированные по стоимости(возврастанию).
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
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
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     * @throws NotFoundException - такого курса не существует
     * @throws CourseSubscribeException - на данный курс пользователь уже подписан
     */
    @PutMapping(value = "/user/subscribeCourse")
    @ResponseBody
    public boolean subscribeToCourse(@RequestParam String courseName) throws NotFoundException, NotAuthenticatedException, CourseSubscribeException {
        userControllerLogger.info("start subscribeToCourse");
        return userService.subscribeToCourse(courseName);
    }

    /**
     * Метод для подписки пользователя на занятие. Доступен аутентифицированным пользователям.
     * Автоматиески подписывает на все занятия в курсе к которому относится занятие
     * @param lessonDescription - описание занятия на которое планирует подписаться юзер
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     * @throws NotFoundException - такого курса не существует
     * @throws LessonSubscribeException - на данное занятие пользователь уже подписан
     */
    @PutMapping(value = "/user/subscribeLesson")
    @ResponseBody
    public boolean subscribeToLesson(@RequestParam String lessonDescription) throws NotFoundException, NotAuthenticatedException, LessonSubscribeException {
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
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     * @throws InvalidDateException - не корректная дата
     * @throws CourseExistException - если такой курс уже существует
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
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     * @throws InvalidDateException - не корректная дата
     * @throws CourseExistException - если такой курс уже существует
     * @throws LessonExistException - если такое занятие уже существует
     */
    @PostMapping(value = "/user/createLesson")
    @ResponseBody
    public boolean createLesson(@RequestBody CreateLessonDto createLessonDto) throws NotAuthenticatedException, InvalidDateException, CourseExistException, LessonExistException {
        userControllerLogger.info("start createLesson");
        return userService.createLesson(createLessonDto.getLessonName(), createLessonDto.getCourseName(), createLessonDto.getLessonDescription(), createLessonDto.getCost(), createLessonDto.getStartDate(), createLessonDto.getEndDate());
    }

    /**
     * Метод для просмотра расписания пользователя. Доступен аутентифицированным пользователям.
     * @throws NotAuthenticatedException - если пользователь не аутентифицирован
     * @throws NotFoundException - если занятия у пользователя отсутсвуют
     */
    @GetMapping(value = "/user/schedule")
    @ResponseBody
    public List<ScheduleLessonDto> getSchedule() throws NotAuthenticatedException, NotFoundException {
        userControllerLogger.info("start getSchedule");
        return userService.getSchedule();
    }

    /**
     * Метод для назначения награды преподавателю. Доступен только пользователям с ролью USER.
     * Награду возможно добавить только педагогу(тоесть роль ADMIN)
     * @param awardReceiveDto - описание награды
     *               Пример описания json:
     *               {
     *                  "login":"user",
     *                  "award":"60",
     *               }
     * @throws NotFoundException - пользователя с таким логином не существует
     * @throws IncorrectAssignAward - если пытаются назначить награду юзеру с ролью USER
     */
    @PutMapping(value = "/user/award")
    @ResponseBody
    public boolean assignAward(@RequestBody AwardReceiveDto awardReceiveDto) throws NotFoundException, IncorrectAssignAward {
        userControllerLogger.info("start assignAward");
        return userService.assignAward(awardReceiveDto.getAward(), awardReceiveDto.getLogin());
    }
}
