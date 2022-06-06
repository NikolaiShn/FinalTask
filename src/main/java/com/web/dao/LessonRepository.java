package com.web.dao;

import com.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Override
    @Transactional
    List<Lesson> findAll();

    @Query("SELECT lessons FROM Lesson lessons WHERE lessons.lessonForm.formName = :lessonForm")
    @Transactional
    List<Lesson> findByLessonForm(@Param("lessonForm") String lessonForm);

    @Transactional
    @Query("SELECT lessons FROM Lesson lessons WHERE lessons.course.courseName = :courseName")
    List<Lesson> findLessonsByCourseFetch(@Param("courseName") String courseName);


}
