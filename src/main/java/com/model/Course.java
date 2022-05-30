package com.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "course_name")
    private String courseName;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseReview> reviews;
    private Double cost;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    public void addCourseReview(CourseReview courseReview) {
        reviews.add(courseReview);
        courseReview.setCourse(this);
    }
    public void removeCourseReview(CourseReview courseReview) {
        reviews.remove(courseReview);
        courseReview.setCourse(null);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setCourse(this);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.setCourse(null);
    }
}
