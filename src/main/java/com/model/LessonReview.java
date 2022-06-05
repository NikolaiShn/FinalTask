package com.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity(name = "lessons_reviews")
public class LessonReview {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String text;
    @ToString.Exclude
    @ManyToOne
    private Lesson lesson;
}
