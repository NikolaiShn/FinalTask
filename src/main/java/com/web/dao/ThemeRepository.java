package com.web.dao;

import com.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findByThemeName(String themeName);

    @Override
    @Transactional
    List<Theme> findAll();

    @Transactional
    @Query("SELECT theme from Theme theme where theme.themeName =:themeName AND theme.knowledgeDirectory.name =:knowledgeDirectoryName")
    Theme findThemeByNameAndKnowledgeDirectory(@Param("themeName") String themeName, @Param("knowledgeDirectoryName") String knowledgeDirectoryName);

}
