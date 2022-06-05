package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    @JsonIgnore
    private Integer id;
    private String courseName;
    @JsonIgnore
    private List<LessonDto> lessons;
    private List<CourseReviewDto> reviews;
    private Double cost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
