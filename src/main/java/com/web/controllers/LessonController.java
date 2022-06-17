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

    @GetMapping(value = "/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getAllLessons() {
        lessonControllerLogger.info("start getAllLessons");
        lessonControllerLogger.info("end getAllLessons");
        return lessonService.getAllLessons();
    }

    @GetMapping(value = "/lessons/{lessonForm}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getByLessonForm(@PathVariable("lessonForm") String lessonForm) {
        lessonControllerLogger.info("start getByLessonForm");
        lessonControllerLogger.info("end getByLessonForm");
        return lessonService.findByLessonForm(lessonForm);
    }

    @GetMapping(value = "/lessons", params = {"courseName"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getLessonsByCourse(@RequestParam String courseName) {
        lessonControllerLogger.info("start getLessonsByCourse");
        lessonControllerLogger.info("end getLessonsByCourse");
        return lessonService.getLessonsByCourse(courseName);
    }

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

    @PutMapping(value = "/lessons/editName")
    @ResponseBody
    public boolean editLessonDescription(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start editLessonDescription");
        lessonControllerLogger.info("end editLessonDescription");
        return lessonService.editLessonName(oldName, newName, courseName);
    }

    @PutMapping(value = "/lessons/editCost")
    @ResponseBody
    public boolean editLessonCost(@RequestParam("lessonName") String lessonName, @RequestParam("newCost") Double newCost, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start editLessonCost");
        lessonControllerLogger.info("end editLessonCost");
        return lessonService.editLessonCost(lessonName, newCost, courseName);
    }

    @DeleteMapping(value = "/lessons/delete")
    @ResponseBody
    public boolean deleteLesson(@RequestParam("lessonName") String lessonName, @RequestParam("courseName") String courseName) throws NotFoundException {
        lessonControllerLogger.info("start deleteLesson");
        lessonControllerLogger.info("end deleteLesson");
        return lessonService.deleteLesson(lessonName, courseName);
    }

    @PostMapping(value = "/lessons/createReview")
    @ResponseBody
    public boolean createLessonReview(@RequestBody LessonReviewReceiveDto lessonReviewReceiveDto) throws NotFoundException {
        lessonControllerLogger.info("start createLessonReview");
        lessonControllerLogger.info("end createLessonReview");
        return lessonService.createLessonReview(lessonReviewReceiveDto.getLessonDescription(), lessonReviewReceiveDto.getReviewText());
    }
}
