package com.web.controllers;

import com.dto.LessonDto;
import com.dto.LessonDtoReceive;
import com.dto.LessonReviewReceiveDto;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.web.services.LessonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {

    private static final Logger lessonControllerLogger = LogManager.getLogger(LessonController.class);

    @Autowired
    private LessonService lessonService;

    /**
     * Метод для просмотра занятий.
     */
    @GetMapping(value = "/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getAllLessons() {
        lessonControllerLogger.info("start getAllLessons");
        lessonControllerLogger.info("end getAllLessons");
        return lessonService.getAllLessons();
    }

    /**
     * Метод для просмотра занятий, у которых форма занятия lessonForm.
     * @param lessonForm - форма занятия
     */
    @GetMapping(value = "/lessons/{lessonForm}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getByLessonForm(@PathVariable("lessonForm") String lessonForm) {
        lessonControllerLogger.info("start getByLessonForm");
        lessonControllerLogger.info("end getByLessonForm");
        return lessonService.findByLessonForm(lessonForm);
    }

    /**
     * Метод для просмотра всех занятий курса.
     * @param courseName - название курса
     */
    @GetMapping(value = "/lessons", params = {"courseName"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getLessonsByCourse(@RequestParam String courseName) {
        lessonControllerLogger.info("start getLessonsByCourse");
        lessonControllerLogger.info("end getLessonsByCourse");
        return lessonService.getLessonsByCourse(courseName);
    }

    /**
     * Метод для создания занятия. Доступен пользователям с ролью ADMIN.
     * @param lessonDtoReceive - содержит параметры необходимые для создания занятия
     *               Пример описания json:
     *               {
     *                  "lessonName":"lessonName",
     *                  "description":"description",
     *                  "cost":"45",
     *                  "mondayDate":"2022-06-07:12:00:00",
     *                  "tuesdayDate":2022-06-08:12:00:00",
     *                  "wednesdayDate":2022-06-09:12:00:00",
     *                  "thursdayDate":2022-06-10:12:00:00",
     *                  "fridayDate":"2022-06-11:12:00:00"
     *                  "lessonForm":"lessonForm";
     *                  "courseName":"courseName";
     *               }
     */
    @PostMapping(value = "/lessons/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean create(@RequestBody LessonDtoReceive lessonDtoReceive) throws InvalidDateException, IncorrectInputException, NotFoundException {
        lessonControllerLogger.info("start create");
        lessonControllerLogger.info("end create");
        return lessonService.createLesson(lessonDtoReceive.getCourseName(), lessonDtoReceive.getLessonName(),
                                            lessonDtoReceive.getLessonForm(), lessonDtoReceive.getDescription(),
                                            lessonDtoReceive.getMondayDate(), lessonDtoReceive.getTuesdayDate(),
                                            lessonDtoReceive.getWednesdayDate(), lessonDtoReceive.getThursdayDate(),
                                            lessonDtoReceive.getFridayDate(), lessonDtoReceive.getCost());
    }

    /**
     * Метод для создания занятия. Доступен пользователям с ролью ADMIN.
     * @param oldName - текущее название занятия
     * @param newName - новое название занятия
     * @param courseName - название курса к которому относится занятие
     */
    @PutMapping(value = "/lessons/editName")
    @ResponseBody
    public boolean editLessonDescription(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start editLessonDescription");
        lessonControllerLogger.info("end editLessonDescription");
        return lessonService.editLessonName(oldName, newName, courseName);
    }

    /**
     * Метод для изменения стоимости занятия. Доступен пользователям с ролью ADMIN.
     * @param lessonName - текущее название занятия
     * @param newCost - новая стоимость занятия
     * @param courseName - название курса к которому относится занятие
     */
    @PutMapping(value = "/lessons/editCost")
    @ResponseBody
    public boolean editLessonCost(@RequestParam("lessonName") String lessonName, @RequestParam("newCost") Double newCost, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start editLessonCost");
        lessonControllerLogger.info("end editLessonCost");
        return lessonService.editLessonCost(lessonName, newCost, courseName);
    }

    /**
     * Метод для удаления занятия из бд. Доступен пользователям с ролью ADMIN.
     * @param lessonName - текущее название занятия
     * @param courseName - название курса к которому относится занятие
     */
    @DeleteMapping(value = "/lessons/delete")
    @ResponseBody
    public boolean deleteLesson(@RequestParam("lessonName") String lessonName, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start deleteLesson");
        lessonControllerLogger.info("end deleteLesson");
        return lessonService.deleteLesson(lessonName, courseName);
    }

    /**
     * Метод для создания комментария к занятию.
     * @param lessonReviewReceiveDto - содержит параметры необходимые для создания комментария к занятию
     *               Пример описания json:
     *               {
     *                   "lessonDescription":"lessonDescription",
     *                   "reviewText":"reviewText"
     *               }
     */
    @PostMapping(value = "/lessons/createReview")
    @ResponseBody
    public boolean createLessonReview(@RequestBody LessonReviewReceiveDto lessonReviewReceiveDto) throws NotFoundException {
        lessonControllerLogger.info("start createLessonReview");
        lessonControllerLogger.info("end createLessonReview");
        return lessonService.createLessonReview(lessonReviewReceiveDto.getLessonDescription(), lessonReviewReceiveDto.getReviewText());
    }
}
