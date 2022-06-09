package com.web.dao;

import com.model.Course;
import com.model.KnowledgeDirectory;
import com.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findUserByKnowledgeDirectories(KnowledgeDirectory knowledgeDirectory);

    @Transactional
    @Query("SELECT knowledgeDirectories FROM User user INNER JOIN user.knowledgeDirectories knowledgeDirectories WHERE user = :user")
    List<KnowledgeDirectory> findKnowledgeDirectoriesByUserFetch(@Param("user") User user);

    @Transactional
    @Query("SELECT courses FROM User user INNER JOIN user.courses courses WHERE user = :user")
    List<Course> findCoursesByUserFetch(@Param("user") User user);

    @Transactional
    @Query("SELECT l FROM Course c JOIN c.lessons l WHERE c.courseName = :courseName")
    List<Lesson> findLessonsByCourseFetch(@Param("courseName") String courseName);

    User save(User user);
}
