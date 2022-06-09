package com.web.dao;

import com.model.LessonForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonFormRepository extends JpaRepository<LessonForm, Long> {

    LessonForm findByFormName(String formName);
}
