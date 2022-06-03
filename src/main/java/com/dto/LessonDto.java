package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Course;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LessonDto {
    private Integer id;
    private String lessonName;
    private String descripion;
    private List<LessonReviewDto> reviews;
    private Double cost;
    private LocalDateTime mondayDate;
    private LocalDateTime tuesdayDate;
    private LocalDateTime wednesdayDate;
    private LocalDateTime thursdayDate;
    private LocalDateTime fridayDate;
    private LessonFormDto lessonForm;
    @JsonIgnore
    @ToString.Exclude
    private CourseDto course;
}
