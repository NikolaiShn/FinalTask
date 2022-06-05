package com.dto.mappers;

import com.dto.LessonReviewDto;
import com.model.LessonReview;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonReviewMapper {
    LessonReviewMapper INSTANCE = Mappers.getMapper(LessonReviewMapper.class);

    @IterableMapping(qualifiedByName = "dto_without_lesson")
    List<LessonReviewDto> lessonReviewsToLessonReviewDtos (List<LessonReview> lessonReviews);
    @IterableMapping(qualifiedByName = "without_lesson")
    List<LessonReview> lessonReviewDtosToLessonReviews (List<LessonReviewDto> lessonReviewDtos);

    @Named("dto_without_lesson")
    @Mapping(target = "lesson", ignore = true)
    LessonReviewDto lessonReviewToLessonReviewDto (LessonReview lessonReview);
    @Named("without_lesson")
    @Mapping(target = "lesson", ignore = true)
    LessonReview lessonReviewDtoToLessonReview (LessonReviewDto lessonReviewDto);
}
