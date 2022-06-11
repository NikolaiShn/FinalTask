package com.web.services;

import com.dto.LessonDto;
import com.dto.mappers.LessonMapper;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.model.*;
import com.web.dao.CourseRepository;
import com.web.dao.LessonFormRepository;
import com.web.dao.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonFormRepository lessonFormRepository;
    @Autowired
    private LessonMapper lessonMapper;

    @Transactional
    public List<LessonDto> getAllLessons() {
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findAll());
    }

    @Transactional
    public List<LessonDto> findByLessonForm(String lessonForm) {
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findByLessonForm(lessonForm));
    }

    @Transactional
    public List<LessonDto> getLessonsByCourse(String courseName) {
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findLessonsByCourseFetch(courseName));
    }


    @Transactional
    public boolean createLesson(String courseName, String lessonName, String lessonFormName, String description,
                                LocalDateTime mondayDate, LocalDateTime tuesdayDate,
                                LocalDateTime wednesdayDate, LocalDateTime thursdayDate,
                                LocalDateTime fridayDate, Double cost) throws InvalidDateException, IncorrectInputException, NotFoundException {
        if(cost <= 0) {
            throw new IncorrectInputException("Некоррктная стоимость");
        }
        if(mondayDate.isBefore(LocalDateTime.now()) ||
           tuesdayDate.isBefore(LocalDateTime.now()) ||
           wednesdayDate.isBefore(LocalDateTime.now()) ||
           thursdayDate.isBefore(LocalDateTime.now()) ||
           fridayDate.isBefore(LocalDateTime.now())
        ) {
            throw new InvalidDateException("дата некорректна");
        } else {
            Lesson lesson = new Lesson();
            LessonForm lessonForm = lessonFormRepository.findByFormName(lessonFormName);
            Course course = courseRepository.findByCourseName(courseName);
            if(lessonForm == null) {
                throw new NotFoundException("Занятий такого типа нет");
            }
            if(course == null) {
                throw new NotFoundException("Курса не существует");
            }
            lesson.setLessonName(lessonName);
            lesson.setMondayDate(mondayDate);
            lesson.setTuesdayDate(tuesdayDate);
            lesson.setWednesdayDate(wednesdayDate);
            lesson.setThursdayDate(thursdayDate);
            lesson.setFridayDate(fridayDate);
            lesson.setDescription(description);
            lesson.setLessonForm(lessonForm);
            lesson.setCost(cost);
            lesson.setCourse(course);
            lessonRepository.save(lesson);
            return true;
        }
    }

    @Transactional
    public boolean editLessonDescription(String lessonName, String newLessonName) throws NotFoundException {
        Lesson lesson = lessonRepository.findByLessonName(lessonName);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lessonRepository.editLessonName(newLessonName, lessonName);
            return true;
        }
    }

    @Transactional
    public boolean editLessonCost(String lessonName, Double cost) throws NotFoundException {
        Lesson lesson = lessonRepository.findByLessonName(lessonName);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lessonRepository.editLessonCost(lessonName, cost);
            return true;
        }
    }

    @Transactional
    public boolean deleteLesson(String lessonName) throws NotFoundException {
        Lesson lesson = lessonRepository.findByLessonName(lessonName);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lessonRepository.delete(lesson);
            return true;
        }
    }

    @Transactional
    public boolean createLessonReview(String lessonDescription, String reviewText) throws NotFoundException {
        Lesson lesson = lessonRepository.findByDescription(lessonDescription);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            LessonReview lessonReview = new LessonReview(reviewText);
            lesson.addLessonReview(lessonReview);
            return true;
        }
    }
}
