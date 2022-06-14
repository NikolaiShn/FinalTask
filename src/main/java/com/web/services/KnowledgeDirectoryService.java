package com.web.services;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.dto.mappers.KnowledgeDirectoryMapper;
import com.dto.mappers.SectionMapper;
import com.dto.mappers.ThemeMapper;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Section;
import com.model.Theme;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KnowledgeDirectoryService {

    @Autowired
    private KnowledgeDirectoryRepository knowledgeDirectoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KnowledgeDirectoryMapper knowledgeDirectoryMapper;
    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private SectionMapper sectionMapper;


    @Transactional
    public List<KnowledgeDirectoryDto> getAllKnowledgeDirectories() {
        return knowledgeDirectoryMapper.knowledgeDirectoriesToKnowledgeDirectoriesDto(knowledgeDirectoryRepository.findAll());
    }

    @Transactional
    public List<ThemeDto> findKnowledgeDirectoryThemes(String knowledgeDirectoryName) {
        return themeMapper.themesToThemeDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getThemes());
    }

    @Transactional
    public List<SectionDto> findKnowledgeDirectorySections(String knowledgeDirectoryName) {
        return sectionMapper.sectionsToSectionDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getSections());
    }

    @Transactional
    public boolean createKnowLedgeDirectory(String knowledgeDirectoryName) {
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        knowledgeDirectory.setName(knowledgeDirectoryName);
        knowledgeDirectoryRepository.save(knowledgeDirectory);
        return true;
    }

    @Transactional
    public boolean deleteKnowledgeDirectory(String knowledgeDirectoryName) throws NotFoundException {
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            throw new NotFoundException("Такого справочника не существует");
        } else {
            knowledgeDirectoryRepository.delete(knowledgeDirectory);
            return true;
        }
    }

    @Transactional
    public boolean editKnowledgeDirectoryName(String newKnowledgeDirectoryName, String knowledgeDirectoryName) throws NotFoundException {
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            throw new NotFoundException("Такого справочника не существует");
        } else {
            knowledgeDirectoryRepository.editKnowledgeDirectoryName(newKnowledgeDirectoryName, knowledgeDirectoryName);
            return true;
        }
    }

    @Transactional
    public boolean addTheme(String knowledgeDirectoryName, String themeName) throws NotFoundException {
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            throw new NotFoundException("Такого справочника не существует");
        } else {
            Theme theme = new Theme();
            theme.setThemeName(themeName);
            knowledgeDirectory.addTheme(theme);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            return true;
        }
    }

    @Transactional
    public boolean addSection(String knowledgeDirectoryName, String sectionName) throws NotFoundException {
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            throw new NotFoundException("Такого справочника не существует");
        } else {
            Section section = new Section();
            section.setSectionName(sectionName);
            knowledgeDirectory.addSection(section);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            return true;
        }
    }

}
