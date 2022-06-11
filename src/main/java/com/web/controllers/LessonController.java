package com.web.controllers;

import com.dto.LessonDto;
import com.dto.LessonDtoReceive;
import com.dto.LessonReviewReceiveDto;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.web.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping(value = "/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping(value = "/lessons/{lessonForm}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getByLessonForm(@PathVariable("lessonForm") String lessonForm) {
        return lessonService.findByLessonForm(lessonForm);
    }

    @GetMapping(value = "/lessons", params = {"courseName"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LessonDto> getLessonsByCourse(@RequestParam String courseName) {
        return lessonService.getLessonsByCourse(courseName);
    }

    @PostMapping(value = "/lessons/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean create(@RequestBody LessonDtoReceive lessonDtoReceive) throws InvalidDateException, IncorrectInputException, NotFoundException {
        return lessonService.createLesson(lessonDtoReceive.getCourseName(), lessonDtoReceive.getLessonName(),
                                            lessonDtoReceive.getLessonForm(), lessonDtoReceive.getDescripion(),
                                            lessonDtoReceive.getMondayDate(), lessonDtoReceive.getTuesdayDate(),
                                            lessonDtoReceive.getWednesdayDate(), lessonDtoReceive.getThursdayDate(),
                                            lessonDtoReceive.getFridayDate(), lessonDtoReceive.getCost());
    }

    @PutMapping(value = "/lessons/editName")
    @ResponseBody
    public boolean editLessonDescription(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName) throws NotFoundException {
        return lessonService.editLessonDescription(oldName, newName);
    }

    @PutMapping(value = "/lessons/editCost")
    @ResponseBody
    public boolean editLessonCost(@RequestParam("lessonName") String lessonName, @RequestParam("newCost") Double newCost) throws NotFoundException {
        return lessonService.editLessonCost(lessonName, newCost);
    }

    @DeleteMapping(value = "/lessons/delete")
    @ResponseBody
    public boolean deleteLesson(@RequestParam("lessonName") String lessonName) throws NotFoundException {
        return lessonService.deleteLesson(lessonName);
    }

    @PostMapping(value = "/lessons/createReview")
    @ResponseBody
    public boolean createLessonReview(@RequestBody LessonReviewReceiveDto lessonReviewReceiveDto) throws NotFoundException {
        return lessonService.createLessonReview(lessonReviewReceiveDto.getLessonDescription(), lessonReviewReceiveDto.getReviewText());
    }
}
