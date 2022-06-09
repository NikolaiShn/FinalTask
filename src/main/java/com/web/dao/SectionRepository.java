package com.web.dao;

import com.model.Section;
import com.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    Section findBySectionName(String sectionName);

    @Override
    @Transactional
    List<Section> findAll();

    @Modifying
    @Transactional
    @Query("update Section section set section.sectionName =:newName where section.sectionName =:oldName")
    void editSectionName(@Param("newName") String newName, @Param("oldName") String oldName);
}
