package com.dto.mappers;

import com.dto.LessonFormDto;
import com.model.LessonForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonFormMapper {

    LessonFormDto lessonFormToLessonFormDto(LessonForm lessonForm);
    LessonForm lessonFormDtoToLessonForm(LessonFormDto lessonFormDto);
}
