package com.dto.mappers;

import com.dto.KnowledgeDirectoryDto;
import com.model.KnowledgeDirectory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SectionMapper.class, ThemeMapper.class})
public interface KnowledgeDirectoryMapper {
    KnowledgeDirectoryMapper INSTANCE = Mappers.getMapper(KnowledgeDirectoryMapper.class);

    List<KnowledgeDirectoryDto> knowledgeDirectoriesToKnowledgeDirectoriesDto (List<KnowledgeDirectory> knowledgeDirectories);
    List<KnowledgeDirectory> knowledgeDirectoriesDtoToKnowledgeDirectories (List<KnowledgeDirectoryDto> knowledgeDirectoriesDto);
}
