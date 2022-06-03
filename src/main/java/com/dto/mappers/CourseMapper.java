package com.dto.mappers;

import com.dto.CourseDto;
import com.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LessonMapper.class, CourseReviewMapper.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    List<CourseDto> coursesToCourseDtos (List<Course> courses);
    List<Course> courseDtosToCourses (List<CourseDto> courseDtos);
}
