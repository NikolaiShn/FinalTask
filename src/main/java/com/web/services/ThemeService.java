package com.web.services;

import com.dto.ThemeDto;
import com.dto.mappers.ThemeMapper;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Theme;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private KnowledgeDirectoryRepository knowledgeDirectoryRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ThemeMapper themeMapper;


    @Transactional
    public List<ThemeDto> getAllThemes() {
        return themeMapper.themesToThemeDtos(themeRepository.findAll());
    }

    @Transactional
    public boolean createTheme(String knowledgeDirectoryName, String themeName) throws NotFoundException {
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            throw new NotFoundException("Такого справочника не существует");
        } else {
            Theme theme = new Theme();
            theme.setThemeName(themeName);
            theme.setKnowledgeDirectory(knowledgeDirectory);
            themeRepository.save(theme);
            return true;
        }
    }

    @Transactional
    public boolean editThemeName(String themeName, String newThemeName, String knowledgeDirectoryName) throws NotFoundException {
        Theme theme = themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName);
        if(theme == null) {
            throw new NotFoundException("Такой темы не существует");
        } else {
            theme.setThemeName(newThemeName);
            return true;
        }
    }

    @Transactional
    public boolean deleteTheme(String themeName, String knowledgeDirectoryName) throws NotFoundException {
        Theme theme = themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName);
        if(theme == null) {
            throw new NotFoundException("Такой темы не существует");
        } else {
            KnowledgeDirectory knowledgeDirectory = theme.getKnowledgeDirectory();
            knowledgeDirectory.removeTheme(theme);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            return true;
        }
    }
}
