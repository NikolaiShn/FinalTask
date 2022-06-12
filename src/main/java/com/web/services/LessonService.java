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
import java.time.temporal.ChronoUnit;
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
            throw new IncorrectInputException("Некорректная стоимость");
        }
        if((mondayDate.isBefore(LocalDateTime.now()) ||
           tuesdayDate.isBefore(LocalDateTime.now()) ||
           wednesdayDate.isBefore(LocalDateTime.now()) ||
           thursdayDate.isBefore(LocalDateTime.now()) ||
           fridayDate.isBefore(LocalDateTime.now())) &&
                ((ChronoUnit.DAYS.between(tuesdayDate, mondayDate) > 1) ||
                 (ChronoUnit.DAYS.between(wednesdayDate, tuesdayDate) > 1) ||
                 (ChronoUnit.DAYS.between(thursdayDate, wednesdayDate) > 1) ||
                 (ChronoUnit.DAYS.between(fridayDate, thursdayDate) > 1))) {
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
    public boolean editLessonDescription(String lessonName, String newLessonName, String courseName) throws NotFoundException {
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lesson.setLessonName(newLessonName);
            return true;
        }
    }

    @Transactional
    public boolean editLessonCost(String lessonName, Double cost, String courseName) throws NotFoundException {
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lesson.setCost(cost);
            return true;
        }
    }

    @Transactional
    public boolean deleteLesson(String lessonName, String courseName) throws NotFoundException {
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
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
