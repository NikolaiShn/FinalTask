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

    @Modifying
    @Transactional
    @Query("update Theme theme set theme.themeName =:newName where theme.themeName =:oldName")
    void editThemeName(@Param("newName") String newName, @Param("oldName") String oldName);

}