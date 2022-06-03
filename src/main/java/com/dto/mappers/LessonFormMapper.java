package com.dto.mappers;

import com.dto.LessonFormDto;
import com.model.LessonForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonFormMapper {
    LessonFormMapper INSTANCE = Mappers.getMapper(LessonFormMapper.class);

    LessonFormDto lessonFormToLessonFormDto (LessonForm lessonForm);
    LessonForm lessonFormDtoToLessonForm (LessonFormDto lessonFormDto);
}
