package com.dto.mappers;

import com.dto.KnowledgeDirectoryDto;
import com.model.KnowledgeDirectory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SectionMapper.class, ThemeMapper.class})
public interface KnowledgeDirectoryMapper {

    List<KnowledgeDirectoryDto> knowledgeDirectoriesToKnowledgeDirectoriesDto(List<KnowledgeDirectory> knowledgeDirectories);
    List<KnowledgeDirectory> knowledgeDirectoriesDtoToKnowledgeDirectories(List<KnowledgeDirectoryDto> knowledgeDirectoriesDto);
}
