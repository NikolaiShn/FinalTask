package com.dto.mappers;

import com.dto.ProfileCourseDto;
import com.model.Course;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonMapper.class, CourseReviewMapper.class})
public interface ProfileCourseMapper {

    @IterableMapping(qualifiedByName = "dto_without_lessons")
    List<ProfileCourseDto> coursesToProfileCourseDtos(List<Course> courses);
    @IterableMapping(qualifiedByName = "without_lessons")
    List<Course> profileCourseDtosToCourses(List<ProfileCourseDto> courseDtos);

    @Named("dto_without_lessons")
    @Mapping(target = "lessons", ignore = true)
    ProfileCourseDto courseToProfileCourseDto(Course course);

    @Named("without_lessons")
    @Mapping(target = "lessons", ignore = true)
    Course profileCourseDtoToCourse(ProfileCourseDto courseDto);
}
