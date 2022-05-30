package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "course_reviews")
public class CourseReview {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String text;
    @ManyToOne
    private Course course;
}
