package com.web.dao;

import com.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    @Transactional
    List<Course> findAll();
}
