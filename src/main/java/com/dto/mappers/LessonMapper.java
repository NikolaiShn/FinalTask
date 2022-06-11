package com.dto.mappers;

import com.dto.LessonDto;
import com.model.Lesson;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonReviewMapper.class, LessonFormMapper.class})
public interface LessonMapper {

    @IterableMapping(qualifiedByName = "dto_without_course")
    List<LessonDto> lessonsToLessonDtos (List<Lesson> lessons);
    @IterableMapping(qualifiedByName = "without_course")
    List<Lesson> lessonDtosToLessons (List<LessonDto> lessonDtos);

    @Named("dto_without_course")
    @Mapping(target = "course", ignore = true)
    LessonDto lessonToLessonDto (Lesson lesson);
    @Named("without_course")
    @Mapping(target = "course", ignore = true)
    Lesson lessonDtoToLesson (LessonDto lessonDto);
}
