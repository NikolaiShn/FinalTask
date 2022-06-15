package com.web.services;

import com.dto.CourseDto;
import com.dto.mappers.CourseMapper;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.model.Course;
import com.model.CourseReview;
import com.web.dao.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private static final Logger courseServiceLogger = LogManager.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private CourseMapper courseMapper;

    @Transactional
    public List<CourseDto> getAllCourses() {
        courseServiceLogger.info("start getAllCourses");
        courseServiceLogger.info("end getAllCourses");
        return courseMapper.coursesToCourseDtos(courseRepository.findAll());
    }

    @Transactional
    public List<CourseDto>  findByCostGreaterThan(Double cost) {
        courseServiceLogger.info("start findByCostGreaterThan");
        courseServiceLogger.info("end findByCostGreaterThan");
        return courseMapper.coursesToCourseDtos(courseRepository.findByCostGreaterThan(cost));
    }

    @Transactional
    public List<CourseDto> getAllCoursesAllOrderByStartDateDesc() {
        courseServiceLogger.info("start getAllCoursesAllOrderByStartDateDesc");
        courseServiceLogger.info("end getAllCoursesAllOrderByStartDateDesc");
        return courseMapper.coursesToCourseDtos(courseRepository.findAllByOrderByStartDateDesc());
    }

    @Transactional
    public List<CourseDto> getAllCoursesAllOrderByEndDateDesc() {
        courseServiceLogger.info("start getAllCoursesAllOrderByEndDateDesc");
        courseServiceLogger.info("end getAllCoursesAllOrderByEndDateDesc");
        return courseMapper.coursesToCourseDtos(courseRepository.findAllByOrderByEndDateDesc());
    }

    @Transactional
    public boolean createCourse(String courseName, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException {
        courseServiceLogger.info("start createCourse");
        if(endDate.isBefore(startDate) && startDate.isBefore(LocalDateTime.now())) {
            courseServiceLogger.error("Дата не корректна");
            throw new InvalidDateException("Дата не корректна");
        } else {
            courseRepository.save(new Course(courseName, cost, startDate, endDate));
            courseServiceLogger.info("end createCourse");
            return true;
        }
    }

    @Transactional
    public boolean editCourseName(String oldName, String newName) throws NotFoundException {
        courseServiceLogger.info("start editCourseName");
        Course course = courseRepository.findByCourseName(oldName);
        if(course == null) {
            courseServiceLogger.error("Такого курса не существует");
            throw new NotFoundException("Такого курса не существует");
        } else {
            courseRepository.editCourseName(newName, oldName);
            courseServiceLogger.info("end editCourseName");
            return true;
        }
    }

    @Transactional
    public boolean editCourseCost(String courseName, Double cost) throws NotFoundException, IncorrectInputException {
        courseServiceLogger.info("start editCourseCost");
        Course course = courseRepository.findByCourseName(courseName);
        if(cost <= 0) {
            courseServiceLogger.error("Некорректная стоимость");
            throw new IncorrectInputException("Некорректная стоимость");
        }
        if(course == null) {
            courseServiceLogger.error("Такого курса не существует");
            throw new NotFoundException("Такого курса не существует");
        } else {
            courseRepository.editCourseCost(courseName, cost);
            courseServiceLogger.info("end editCourseCost");
            return true;
        }
    }

    @Transactional
    public boolean deleteCourse(String courseName) throws NotFoundException {
        courseServiceLogger.info("start deleteCourse");
        Course course = courseRepository.findByCourseName(courseName);
        if(course == null) {
            courseServiceLogger.error("Такого курса не существует");
            throw new NotFoundException("Такого курса не существует");
        } else {
            courseRepository.delete(course);
            courseServiceLogger.info("end deleteCourse");
            return true;
        }
    }

    @Transactional
    public boolean createCourseReview(String courseName, String reviewText) throws NotFoundException {
        courseServiceLogger.info("start createCourseReview");
        Course course = courseRepository.findByCourseName(courseName);
        if(course == null) {
            courseServiceLogger.error("Такого юзера не существует");
            throw new NotFoundException("Такого юзера не существует");
        } else {
            CourseReview courseReview = new CourseReview(reviewText);
            course.addCourseReview(courseReview);
            courseServiceLogger.info("end createCourseReview");
            return true;
        }
    }
}
