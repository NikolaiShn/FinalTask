package com.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "lessons_review")
public class LessonReview {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String text;
    @ManyToOne
    private Lesson lesson;
}
