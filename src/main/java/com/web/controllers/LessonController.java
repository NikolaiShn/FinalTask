package com.web.controllers;

import com.dto.LessonDto;
import com.web.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
