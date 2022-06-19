package com.web.controllers;

import com.dto.CourseDto;
import com.dto.CourseDtoReceive;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.web.services.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private static final Logger  courseControllerControllerLogger = LogManager.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    /**
     * @return возвращает все курсы для просмотра пользователям
     */
    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getAllCourses() {
        courseControllerControllerLogger.info("start getAllCourses");
        courseControllerControllerLogger.info("end getAllCourses");
        return courseService.getAllCourses();
    }

    /**
     * @param cost граница стоимости курсов
     * @return возвращает все курсы стоимостью выше чем cost
     */
    @GetMapping(value = "/coursesCostGreater/{cost}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CourseDto> getAllCourses(@PathVariable("cost") Double cost) {
        courseControllerControllerLogger.info("start getAllCourses with cost");
        courseControllerControllerLogger.info("end getAllCourses with cost");
        return courseService.findByCostGreaterThan(cost);
    }

    /**
     * @return возвращает все курсы отсортированные по убыванию значения startDate
     *         для просмотра пользователям
     */
    @GetMapping(value = "/coursesSortByStartDate")
    @ResponseBody
    public List<CourseDto> getAllCoursesAllOrderByStartDateDesc() {
        courseControllerControllerLogger.info("start getAllCoursesAllOrderByStartDateDesc ");
        courseControllerControllerLogger.info("end getAllCoursesAllOrderByStartDateDesc ");
        return courseService.getAllCoursesAllOrderByStartDateDesc();
    }

    /**
     * @return возвращает все курсы отсортированные по убыванию значения endDate
     *         для просмотра пользователям
     */
    @GetMapping(value = "/coursesSortByEndDate")
    @ResponseBody
    public List<CourseDto> getAllCoursesAllOrderByEndDateDesc() {
        courseControllerControllerLogger.info("start getAllCoursesAllOrderByEndDateDesc ");
        courseControllerControllerLogger.info("end getAllCoursesAllOrderByEndDateDesc ");
        return courseService.getAllCoursesAllOrderByEndDateDesc();
    }

    /**
     * Метод для добавления курса в бд. Доступен пользователям с ролью ADMIN.
     * @param course содерит параметры необходимые для создания курса.
     *               Пример описания json:
     *               {
     *                  "courseName":"имя курса",
     *                  "cost":"50.5",
     *                  "startDate":"2022-02-02 12:00:00",
     *                  "endDate":"2022-03-02 12:00:00"
     *               }
     */
    @PostMapping(value = "/course/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean create(@RequestBody CourseDtoReceive course) throws InvalidDateException {
        courseControllerControllerLogger.info("start create");
        courseControllerControllerLogger.info("end create");
        return courseService.createCourse(course.getCourseName(), course.getCost(), course.getStartDate(), course.getEndDate());
    }

    /**
     * Метод для изменения имени курса. Доступен пользователям с ролью ADMIN.
     * @param oldName - текущее название курса
     * @param newName - новое название курса
     */
    @PutMapping(value = "/course/editName")
    @ResponseBody
    public boolean editCourseName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName) throws NotFoundException {
        courseControllerControllerLogger.info("start editCourseName");
        courseControllerControllerLogger.info("end editCourseName");
        return courseService.editCourseName(oldName, newName);
    }

    /**
     * Метод для изменения стоимости курса. Доступен пользователям с ролью ADMIN.
     * @param courseName - текущее название курса
     * @param newCost - новая стоимость
     */
    @PutMapping(value = "/course/editCost")
    @ResponseBody
    public boolean editCourseCost(@RequestParam("courseName") String courseName, @RequestParam("newCost") Double newCost) throws NotFoundException, IncorrectInputException {
        courseControllerControllerLogger.info("start editCourseCost");
        courseControllerControllerLogger.info("end editCourseCost");
        return courseService.editCourseCost(courseName, newCost);
    }

    /**
     * Метод для удаления курса из бд. Доступен пользователям с ролью ADMIN.
     * @param courseName - текущее название курса
     */
    @DeleteMapping(value = "/course/delete")
    @ResponseBody
    public boolean deleteCourse(@RequestParam("courseName") String courseName) throws NotFoundException {
        courseControllerControllerLogger.info("start deleteCourse");
        courseControllerControllerLogger.info("end deleteCourse");
        return courseService.deleteCourse(courseName);
    }

    /**
     * Метод для создания отзыва о курсе. Доступен аутентифицированным пользователям.
     * @param courseName - текущее название курса
     * @param reviewText - текст отзыва
     */
    @PostMapping(value = "/course/createReview")
    @ResponseBody
    public boolean createCourseReview(@RequestParam("courseName") String courseName, @RequestParam("reviewText") String reviewText) throws NotFoundException {
        courseControllerControllerLogger.info("start createCourseReview");
        courseControllerControllerLogger.info("end createCourseReview");
        return courseService.createCourseReview(courseName, reviewText);
    }
}
