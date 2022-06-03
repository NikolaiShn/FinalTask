package com.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    private Integer id;
    private String courseName;
    private List<LessonDto> lessons;
    private List<CourseReviewDto> reviews;
    private Double cost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
