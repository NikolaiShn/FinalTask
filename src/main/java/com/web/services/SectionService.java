package com.web.services;

import com.dto.SectionDto;
import com.dto.mappers.SectionMapper;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Section;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.SectionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionService {

    private static final Logger sectionServiceLogger = LogManager.getLogger(SectionService.class);

    @Autowired
    private KnowledgeDirectoryRepository knowledgeDirectoryRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SectionMapper sectionMapper;

    @Transactional
    public List<SectionDto> getAllSections() {
        sectionServiceLogger.info("start getAllSections");
        sectionServiceLogger.info("end getAllSections");
        return sectionMapper.sectionsToSectionDtos(sectionRepository.findAll());
    }

    @Transactional
    public boolean createSection(String knowledgeDirectoryName, String sectionName) throws NotFoundException {
        sectionServiceLogger.info("start createSection");
        KnowledgeDirectory knowledgeDirectory = knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        if(knowledgeDirectory == null) {
            sectionServiceLogger.error("Такого справочника не существует");
            throw new NotFoundException("Такого справочника не существует");
        } else {
            Section section = new Section();
            section.setSectionName(sectionName);
            section.setKnowledgeDirectory(knowledgeDirectory);
            sectionRepository.save(section);
            sectionServiceLogger.info("end createSection");
            return true;
        }
    }

    @Transactional
    public boolean editSectionName(String sectionName, String newSectionName, String knowledgeDirectoryName) throws NotFoundException {
        sectionServiceLogger.info("start editSectionName");
        Section section = sectionRepository.findSectionByNameAndKnowledgeDirectoryName(sectionName, knowledgeDirectoryName);
        if(section == null) {
            sectionServiceLogger.error("Такого раздела не существует");
            throw new NotFoundException("Такого раздела не существует");
        } else {
            section.setSectionName(newSectionName);
            sectionServiceLogger.info("end editSectionName");
            return true;
        }
    }

    @Transactional
    public boolean deleteSection(String sectionName, String knowledgeDirectoryName) throws NotFoundException {
        sectionServiceLogger.info("start deleteSection");
        Section section = sectionRepository.findSectionByNameAndKnowledgeDirectoryName(sectionName, knowledgeDirectoryName);
        if(section == null) {
            sectionServiceLogger.error("Такого раздела не существует");
            throw new NotFoundException("Такого раздела не существует");
        } else {
            KnowledgeDirectory knowledgeDirectory = section.getKnowledgeDirectory();
            knowledgeDirectory.removeSection(section);
            knowledgeDirectoryRepository.save(knowledgeDirectory);
            sectionServiceLogger.info("end deleteSection");
            return true;
        }
    }
}
