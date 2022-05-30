package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "description")
    private String descripion;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LessonReview> reviews;
    @Column
    private Double cost;
    @Column(name = "monday_date")
    private LocalDateTime mondayDate;
    @Column(name = "tuesday_date")
    private LocalDateTime tuesdayDate;
    @Column(name = "wednesday_date")
    private LocalDateTime wednesdayDate;
    @Column(name = "thursday_date")
    private LocalDateTime thursdayDate;
    @Column(name = "friday_date")
    private LocalDateTime fridayDate;
    @ManyToOne
    @JoinColumn(name = "lesson_form_id", foreignKey = @ForeignKey(name = "lessons_ibfk_2"))
    private LessonForm lessonForm;
    @ManyToOne
    private Course course;

    public void addLessonReview(LessonReview lessonReview) {
        reviews.add(lessonReview);
        lessonReview.setLesson(this);
    }

    public void removeLessonReview(LessonReview lessonReview) {
        reviews.remove(lessonReview);
        lessonReview.setLesson(null);
    }
}
