package com.dto.mappers;

import com.dto.CourseReviewDto;
import com.model.CourseReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseReviewMapper {
    CourseReviewMapper INSTANCE = Mappers.getMapper(CourseReviewMapper.class);

    @Mapping(target = "course", ignore = true)
    List<CourseReviewDto> coursesReviewsToCourseReviewDtos (List<CourseReview> courseReviews);
    @Mapping(target = "course", ignore = true)
    List<CourseReview> courseReviewDtosToCoursesReviews (List<CourseReviewDto> courseReviewDtos);
}
