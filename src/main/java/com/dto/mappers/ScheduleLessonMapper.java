package com.dto.mappers;

import com.dto.ScheduleLessonDto;
import com.model.Lesson;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = LessonMapper.class)
public interface ScheduleLessonMapper {
    ScheduleLessonMapper INSTANSE = Mappers.getMapper(ScheduleLessonMapper.class);

    //@IterableMapping(qualifiedByName = "without_id_cost_lessonForm_course")
    List<ScheduleLessonDto> lessonsToScheduleLessonDtos(List<Lesson> lessons);

//    @Named("without_id_cost_lessonForm_course")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "cost", ignore = true)
//    @Mapping(target = "lessonForm", ignore = true)
//    @Mapping(target = "course", ignore = true)
    ScheduleLessonDto lessonToScheduleLessonDto(Lesson lesson);
}
