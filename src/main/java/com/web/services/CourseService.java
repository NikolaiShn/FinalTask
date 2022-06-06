package com.web.services;

import com.dto.CourseDto;
import com.dto.LessonDto;
import com.dto.mappers.CourseMapper;
import com.dto.mappers.LessonMapper;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.model.Course;
import com.web.controllers.AuthenticationFacade;
import com.web.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Transactional
    public List<CourseDto> getAllCourses() {
        return CourseMapper.INSTANCE.coursesToCourseDtos(courseRepository.findAll());
    }

    @Transactional
    public List<CourseDto>  findByCostGreaterThan(Double cost) {
        return CourseMapper.INSTANCE.coursesToCourseDtos(courseRepository.findByCostGreaterThan(cost));
    }

    @Transactional
    public List<CourseDto> getAllCoursesAllOrderByStartDateDesc() {
        return CourseMapper.INSTANCE.coursesToCourseDtos(courseRepository.findAllByOrderByStartDateDesc());
    }

    @Transactional
    public List<CourseDto> getAllCoursesAllOrderByEndDateDesc() {
        return CourseMapper.INSTANCE.coursesToCourseDtos(courseRepository.findAllByOrderByEndDateDesc());
    }

    @Transactional
    public boolean createCourse(String courseName, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException {
        if(endDate.isBefore(startDate)) {
            throw new InvalidDateException("Дата не корректна");
        } else {
            courseRepository.save(new Course(courseName, cost, startDate, endDate));
            return true;
        }
    }

    @Transactional
    public boolean editCourseName(String oldName, String newName) throws NotFoundException {
        Course course = courseRepository.findByCourseName(oldName);
        if(course == null) {
            throw new NotFoundException("Такого юзера не существует");
        } else {
            courseRepository.editCourseName(newName, oldName);
            return true;
        }
    }

    @Transactional
    public boolean editCourseCost(String courseName, Double cost) throws NotFoundException, IncorrectInputException {
        Course course = courseRepository.findByCourseName(courseName);
        if(cost <= 0) {
            throw new IncorrectInputException("Некоррктная стоимость");
        }
        if(course == null) {
            throw new NotFoundException("Такого юзера не существует");
        } else {
            courseRepository.editCourseCost(courseName, cost);
            return true;
        }
    }

    //TODO сделать функцию, которая удаляет даже если есть ссылки на юзера
    //Удаляет те у которых нету ссылок на юзера и удаляет урок
    @Transactional
    public boolean deleteCourse(String courseName) throws NotFoundException {
        Course course = courseRepository.findByCourseName(courseName);
        if(course == null) {
            throw new NotFoundException("Такого юзера не существует");
        } else {
            courseRepository.delete(course);
            return true;
        }
    }
}
