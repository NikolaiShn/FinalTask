package com.web.services;

import com.dto.CourseDto;
import com.dto.mappers.CourseMapper;
import com.web.controllers.AuthenticationFacade;
import com.web.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
}
