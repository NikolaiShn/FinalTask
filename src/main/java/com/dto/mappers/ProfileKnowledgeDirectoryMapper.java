package com.dto.mappers;

import com.dto.ProfileKnowledgeDirectoryDto;
import com.model.KnowledgeDirectory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SectionMapper.class, ThemeMapper.class})
public interface ProfileKnowledgeDirectoryMapper {

    List<ProfileKnowledgeDirectoryDto> knowledgeDirectoriesToProfileKnowledgeDirectoriesDto(List<KnowledgeDirectory> knowledgeDirectories);
    List<KnowledgeDirectory> knowledgeDirectoriesDtoToKnowledgeDirectories(List<ProfileKnowledgeDirectoryDto> profileKnowledgeDirectoriesDto);
}
