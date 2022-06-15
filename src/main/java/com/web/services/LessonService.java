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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LessonService {

    private static final Logger lessonServiceLogger = LogManager.getLogger(LessonService.class);

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
        lessonServiceLogger.info("start getAllLessons");
        lessonServiceLogger.info("end getAllLessons");
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findAll());
    }

    @Transactional
    public List<LessonDto> findByLessonForm(String lessonForm) {
        lessonServiceLogger.info("start findByLessonForm");
        lessonServiceLogger.info("end findByLessonForm");
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findByLessonForm(lessonForm));
    }

    @Transactional
    public List<LessonDto> getLessonsByCourse(String courseName) {
        lessonServiceLogger.info("start getLessonsByCourse");
        lessonServiceLogger.info("end getLessonsByCourse");
        return lessonMapper.lessonsToLessonDtos(lessonRepository.findLessonsByCourseFetch(courseName));
    }

    @Transactional
    public boolean createLesson(String courseName, String lessonName, String lessonFormName, String description,
                                LocalDateTime mondayDate, LocalDateTime tuesdayDate,
                                LocalDateTime wednesdayDate, LocalDateTime thursdayDate,
                                LocalDateTime fridayDate, Double cost) throws InvalidDateException, IncorrectInputException, NotFoundException {
        lessonServiceLogger.info("start createLesson");
        if(cost <= 0) {
            lessonServiceLogger.error("Некорректная стоимость");
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
            lessonServiceLogger.error("дата некорректна");
            throw new InvalidDateException("дата некорректна");
        } else {
            Lesson lesson = new Lesson();
            LessonForm lessonForm = lessonFormRepository.findByFormName(lessonFormName);
            Course course = courseRepository.findByCourseName(courseName);
            if(lessonForm == null) {
                lessonServiceLogger.error("Занятий такого типа нет");
                throw new NotFoundException("Занятий такого типа нет");
            }
            if(course == null) {
                lessonServiceLogger.error("Курса не существует");
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
            lessonServiceLogger.info("end createLesson");
            return true;
        }
    }

    @Transactional
    public boolean editLessonName(String lessonName, String newLessonName, String courseName) throws NotFoundException {
        lessonServiceLogger.info("start editLessonName");
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
        if(lesson == null) {
            lessonServiceLogger.error("Такого занятия не существует");
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lesson.setLessonName(newLessonName);
            lessonServiceLogger.info("end editLessonName");
            return true;
        }
    }

    @Transactional
    public boolean editLessonCost(String lessonName, Double cost, String courseName) throws NotFoundException {
        lessonServiceLogger.info("start editLessonCost");
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
        if(lesson == null) {
            lessonServiceLogger.error("Такого занятия не существует");
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lesson.setCost(cost);
            lessonServiceLogger.info("end editLessonCost");
            return true;
        }
    }

    @Transactional
    public boolean deleteLesson(String lessonName, String courseName) throws NotFoundException {
        lessonServiceLogger.info("start deleteLesson");
        Lesson lesson = lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName);
        if(lesson == null) {
            lessonServiceLogger.error("Такого занятия не существует");
            throw new NotFoundException("Такого занятия не существует");
        } else {
            lessonRepository.delete(lesson);
            lessonServiceLogger.info("end deleteLesson");
            return true;
        }
    }

    @Transactional
    public boolean createLessonReview(String lessonDescription, String reviewText) throws NotFoundException {
        lessonServiceLogger.info("start createLessonReview");
        Lesson lesson = lessonRepository.findByDescription(lessonDescription);
        if(lesson == null) {
            lessonServiceLogger.error("Такого занятия не существует");
            throw new NotFoundException("Такого занятия не существует");
        } else {
            LessonReview lessonReview = new LessonReview(reviewText);
            lesson.addLessonReview(lessonReview);
            lessonServiceLogger.info("end createLessonReview");
            return true;
        }
    }
}
