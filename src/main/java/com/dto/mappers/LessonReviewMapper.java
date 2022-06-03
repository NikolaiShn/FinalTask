package com.dto.mappers;

import com.dto.LessonReviewDto;
import com.model.LessonReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonReviewMapper {
    LessonReviewMapper INSTANCE = Mappers.getMapper(LessonReviewMapper.class);

    @Mapping(target = "lesson", ignore = true)
    List<LessonReviewDto> lessonReviewsToLessonReviewDtos (List<LessonReview> lessonReviews);
    @Mapping(target = "lesson", ignore = true)
    List<LessonReview> lessonReviewDtosToLessonReviews (List<LessonReviewDto> lessonReviewDtos);
}
