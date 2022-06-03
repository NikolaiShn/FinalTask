package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
public class CourseReviewDto {
    private Long id;
    private String text;
    @JsonIgnore
    @ToString.Exclude
    private CourseDto course;
}
