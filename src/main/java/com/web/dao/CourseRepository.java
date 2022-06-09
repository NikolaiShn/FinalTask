package com.web.dao;

import com.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Override
    @Transactional
    List<Course> findAll();

    @Modifying
    @Transactional
    @Query("update Course course set course.courseName = :newName where course.courseName =:oldName")
    void editCourseName(@Param("newName") String newName, @Param("oldName") String oldName);

    @Modifying
    @Transactional
    @Query("update Course course set course.cost = :cost where course.courseName =:courseName")
    void editCourseCost(@Param("courseName") String courseName, @Param("cost") Double cost);

    Course findByCourseName(String name);

    List<Course> findByCostGreaterThan(Double cost);

    List<Course> findAllByOrderByStartDateDesc();

    List<Course> findAllByOrderByEndDateDesc();

    @Modifying
    @Transactional
    @Query("delete from Course course where course.courseName =:courseName")
    void deleteByCourseName(@Param("courseName") String courseName);
}
