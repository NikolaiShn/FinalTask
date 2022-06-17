package com.web.services;

import com.dto.ThemeDto;
import com.dto.mappers.ThemeMapper;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Theme;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.ThemeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeService {

    private static final Logger themeServiceLogger = LogManager.getLogger(ThemeService.class);

    @Autowired
    private KnowledgeDirectoryRepository knowledgeDirectoryRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ThemeMapper themeMapper;


    @Transactional
    public List<ThemeDto> getAllThemes() {
        themeServiceLogger.info("start getAllThemes");
        themeServiceLogger.info("end getAllThemes");
        return themeMapper.themesToThemeDtos(themeRepository.findAll());
    }

    @Transactional
    public boolean createTheme(String knowledgeDirectoryName, String themeName) throws NotFoundException {
        themeServiceLogger.info("start createTheme");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if (knowledgeDirectory == null) {
            themeServiceLogger.error("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            Theme theme = new Theme();
            theme.setThemeName(themeName);
            theme.setKnowledgeDirectory(knowledgeDirectory);
            themeRepository.save(theme);
            themeServiceLogger.info("end createTheme");
            return true;
        }
    }

    @Transactional
    public boolean editThemeName(String themeName, String newThemeName, String knowledgeDirectoryName) throws NotFoundException {
        themeServiceLogger.info("start editThemeName");
        List<Theme> themes = themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName);
        if (themes.isEmpty()) {
            themeServiceLogger.error("Такой темы не существует");
            throw new NotFoundException("Такой темы не существует");
        } else {
            for(Theme theme : themes) {
                theme.setThemeName(newThemeName);
            }
            themeServiceLogger.info("end editThemeName");
            return true;
        }
    }

    @Transactional
    public boolean deleteTheme(String themeName, String knowledgeDirectoryName) throws NotFoundException {
        themeServiceLogger.info("start deleteTheme");
        List<Theme> themes = themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName);
        if (themes.isEmpty()) {
            themeServiceLogger.error("Такой темы не существует");
            throw new NotFoundException("Такой темы не существует");
        } else {
            for(Theme theme : themes) {
                KnowledgeDirectory knowledgeDirectory = theme.getKnowledgeDirectory();
                knowledgeDirectory.removeTheme(theme);
                knowledgeDirectoryRepository.save(knowledgeDirectory);
            }
            themeServiceLogger.info("end deleteTheme");
            return true;
        }
    }
}
