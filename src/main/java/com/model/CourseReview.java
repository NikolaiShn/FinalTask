package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "course_reviews")
public class CourseReview implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String text;
    @ToString.Exclude
    @ManyToOne
    private Course course;

    public CourseReview(String reviewText) {
        this.text = reviewText;
    }
}
