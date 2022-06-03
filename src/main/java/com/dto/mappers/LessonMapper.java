package com.dto.mappers;

import com.dto.LessonDto;
import com.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LessonReviewMapper.class, LessonFormMapper.class})
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(target = "course", ignore = true)
    List<LessonDto> lessonsToLessonDtos (List<Lesson> lessons);
    @Mapping(target = "course", ignore = true)
    List<Lesson> lessonDtosToLessons (List<LessonDto> lessonDtos);
}
