package com.web.dao;

import com.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson findByLessonName(String lessonName);

    Lesson findByDescription(String description);

    @Override
    @Transactional
    List<Lesson> findAll();

    @Query("SELECT lessons FROM Lesson lessons WHERE lessons.lessonForm.formName = :lessonForm")
    @Transactional
    List<Lesson> findByLessonForm(@Param("lessonForm") String lessonForm);

    @Transactional
    @Query("SELECT lessons FROM Lesson lessons WHERE lessons.course.courseName = :courseName")
    List<Lesson> findLessonsByCourseFetch(@Param("courseName") String courseName);

    @Modifying
    @Transactional
    @Query("update Lesson lesson set lesson.lessonName =:newName where lesson.lessonName =:oldName")
    void editLessonName(@Param("newName") String newName, @Param("oldName")String oldName);

    @Modifying
    @Transactional
    @Query("update Lesson lesson set lesson.cost =:newCost where lesson.lessonName =:lessonName")
    void editLessonCost(@Param("lessonName") String lessonName, @Param("newCost")Double newCost);
}
