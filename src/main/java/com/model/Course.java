package com.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "course_name")
    private String courseName;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseReview> reviews;
    private Double cost;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Course(String courseName, Double cost, LocalDateTime startDate, LocalDateTime endDate) {
        this.courseName = courseName;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

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
