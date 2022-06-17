package com.dto.mappers;

import com.dto.CourseReviewDto;
import com.model.CourseReview;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseReviewMapper {

    @IterableMapping(qualifiedByName = "dto_without_course")
    List<CourseReviewDto> coursesReviewsToCourseReviewDtos(List<CourseReview> courseReviews);
    @IterableMapping(qualifiedByName = "without_course")
    List<CourseReview> courseReviewDtosToCoursesReviews(List<CourseReviewDto> courseReviewDtos);

    @Named("dto_without_course")
    @Mapping(target = "course", ignore = true)
    CourseReviewDto coursesReviewToCourseReviewDto(CourseReview courseReview);

    @Named("without_course")
    @Mapping(target = "course", ignore = true)
    CourseReview courseReviewDtoToCoursesReview(CourseReviewDto courseReviewDto);
}
