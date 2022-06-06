package com.web.services;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.dto.mappers.KnowledgeDirectoryMapper;
import com.dto.mappers.SectionMapper;
import com.dto.mappers.ThemeMapper;
import com.web.dao.KnowledgeDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class KnowledgeDirectoryService {

    @Autowired
    private KnowledgeDirectoryRepository knowledgeDirectoryRepository;


    @Transactional
    public List<KnowledgeDirectoryDto> getAllKnowledgeDirectories() {
        return KnowledgeDirectoryMapper.INSTANCE.knowledgeDirectoriesToKnowledgeDirectoriesDto(knowledgeDirectoryRepository.findAll());
    }

    @Transactional
    public List<ThemeDto> findKnowledgeDirectoryThemes(String knowledgeDirectoryName) {
        return ThemeMapper.INSTANCE.themesToThemeDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getThemes());
    }

    @Transactional
    public List<SectionDto> findKnowledgeDirectorySections(String knowledgeDirectoryName) {
        return SectionMapper.INSTANCE.sectionsToSectionDtos(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName).getSections());
    }

}
