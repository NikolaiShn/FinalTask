package com.dto.mappers;

import com.dto.CourseDto;
import com.model.Course;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LessonMapper.class, CourseReviewMapper.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @IterableMapping(qualifiedByName = "dto_without_lessons")
    List<CourseDto> coursesToCourseDtos (List<Course> courses);
    @IterableMapping(qualifiedByName = "without_lessons")
    List<Course> courseDtosToCourses (List<CourseDto> courseDtos);

    @Named("dto_without_lessons")
    @Mapping(target = "lessons", ignore = true)
    CourseDto courseToCourseDto (Course course);

    @Named("without_lessons")
    @Mapping(target = "lessons", ignore = true)
    Course courseDtoToCourse (CourseDto courseDto);
}
