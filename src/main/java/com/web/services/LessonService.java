package com.web.services;

import com.dto.LessonDto;
import com.dto.mappers.LessonMapper;
import com.exceptions.InvalidDateException;
import com.model.Lesson;
import com.web.dao.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Transactional
    public List<LessonDto> getAllLessons() {
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessonRepository.findAll());
    }

    @Transactional
    public List<LessonDto> findByLessonForm(String lessonForm) {
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessonRepository.findByLessonForm(lessonForm));
    }

    @Transactional
    public List<LessonDto> getLessonsByCourse(String courseName) {
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessonRepository.findLessonsByCourseFetch(courseName));
    }


    @Transactional
    public boolean createLesson(Lesson newLesson) throws InvalidDateException {
        if(newLesson.getMondayDate().isBefore(LocalDateTime.now()) ||
           newLesson.getTuesdayDate().isBefore(LocalDateTime.now()) ||
           newLesson.getWednesdayDate().isBefore(LocalDateTime.now()) ||
           newLesson.getTuesdayDate().isBefore(LocalDateTime.now()) ||
           newLesson.getFridayDate().isBefore(LocalDateTime.now())
        ) {
            throw new InvalidDateException("дата некорректна");
        } else {
            lessonRepository.save(newLesson);
            return true;
        }
    }
}
