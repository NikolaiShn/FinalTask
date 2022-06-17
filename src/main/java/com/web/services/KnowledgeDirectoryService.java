package com.web.services;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.dto.mappers.KnowledgeDirectoryMapper;
import com.dto.mappers.SectionMapper;
import com.dto.mappers.ThemeMapper;
import com.exceptions.NotFoundException;
import com.exceptions.SectionExistException;
import com.exceptions.ThemeExistException;
import com.model.KnowledgeDirectory;
import com.model.Section;
import com.model.Theme;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KnowledgeDirectoryService {

    private static final Logger knowledgeDirectoryServiceLogger = LogManager.getLogger(KnowledgeDirectoryService.class);

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
        knowledgeDirectoryServiceLogger.info("start getAllKnowledgeDirectories");
        knowledgeDirectoryServiceLogger.info("end getAllKnowledgeDirectories");
        return knowledgeDirectoryMapper.knowledgeDirectoriesToKnowledgeDirectoriesDto(knowledgeDirectoryRepository.findAll());
    }

    @Transactional
    public List<ThemeDto> findKnowledgeDirectoryThemes(String knowledgeDirectoryName) {
        knowledgeDirectoryServiceLogger.info("start findKnowledgeDirectoryThemes");
        knowledgeDirectoryServiceLogger.info("end findKnowledgeDirectoryThemes");
        return themeMapper.themesToThemeDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getThemes());
    }

    @Transactional
    public List<SectionDto> findKnowledgeDirectorySections(String knowledgeDirectoryName) {
        knowledgeDirectoryServiceLogger.info("start findKnowledgeDirectorySections");
        knowledgeDirectoryServiceLogger.info("end findKnowledgeDirectorySections");
        return sectionMapper.sectionsToSectionDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getSections());
    }

    @Transactional
    public boolean createKnowLedgeDirectory(String knowledgeDirectoryName) {
        knowledgeDirectoryServiceLogger.info("start createKnowLedgeDirectory");
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        knowledgeDirectory.setName(knowledgeDirectoryName);
        knowledgeDirectoryRepository.save(knowledgeDirectory);
        knowledgeDirectoryServiceLogger.info("end createKnowLedgeDirectory");
        return true;
    }

    @Transactional
    public boolean deleteKnowledgeDirectory(String knowledgeDirectoryName) throws NotFoundException {
        knowledgeDirectoryServiceLogger.info("start deleteKnowledgeDirectory");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if (knowledgeDirectory == null) {
            knowledgeDirectoryServiceLogger.error("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            knowledgeDirectoryServiceLogger.info("end deleteKnowledgeDirectory");
            knowledgeDirectoryRepository.delete(knowledgeDirectory);
            return true;
        }
    }

    @Transactional
    public boolean editKnowledgeDirectoryName(String newKnowledgeDirectoryName, String knowledgeDirectoryName) throws NotFoundException {
        knowledgeDirectoryServiceLogger.info("start editKnowledgeDirectoryName");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if (knowledgeDirectory == null) {
            knowledgeDirectoryServiceLogger.info("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            knowledgeDirectoryRepository.editKnowledgeDirectoryName(newKnowledgeDirectoryName, knowledgeDirectoryName);
            knowledgeDirectoryServiceLogger.info("end editKnowledgeDirectoryName");
            return true;
        }
    }

    @Transactional
    public boolean addTheme(String knowledgeDirectoryName, String themeName) throws NotFoundException, ThemeExistException {
        knowledgeDirectoryServiceLogger.info("start addTheme");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if (knowledgeDirectory == null) {
            knowledgeDirectoryServiceLogger.error("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            for(Theme theme : knowledgeDirectory.getThemes()) {
                if(theme.getThemeName().equals(themeName)) {
                    throw new ThemeExistException("Такая тема в справочнике существует");
                }
            }
            Theme theme = new Theme();
            theme.setThemeName(themeName);
            knowledgeDirectory.addTheme(theme);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            knowledgeDirectoryServiceLogger.info("end addTheme");
            return true;
        }
    }

    @Transactional
    public boolean addSection(String knowledgeDirectoryName, String sectionName) throws NotFoundException, SectionExistException {
        knowledgeDirectoryServiceLogger.info("start addSection");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if (knowledgeDirectory == null) {
            knowledgeDirectoryServiceLogger.error("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            for(Section section : knowledgeDirectory.getSections()) {
                if(section.getSectionName().equals(sectionName)) {
                    throw new SectionExistException("Такой раздел в справочнике существует");
                }
            }
            Section section = new Section();
            section.setSectionName(sectionName);
            knowledgeDirectory.addSection(section);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            knowledgeDirectoryServiceLogger.info("end addSection");
            return true;
        }
    }
}
