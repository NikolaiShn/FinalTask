package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
public class LessonReviewDto {
    private Long id;
    private String text;
    @JsonIgnore
    @ToString.Exclude
    private LessonDto lesson;
}
