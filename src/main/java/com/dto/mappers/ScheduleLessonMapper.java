package com.dto.mappers;

import com.dto.ScheduleLessonDto;
import com.model.Lesson;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring", uses = LessonMapper.class)
public interface ScheduleLessonMapper {

    List<ScheduleLessonDto> lessonsToScheduleLessonDtos(List<Lesson> lessons);

    ScheduleLessonDto lessonToScheduleLessonDto(Lesson lesson);
}
