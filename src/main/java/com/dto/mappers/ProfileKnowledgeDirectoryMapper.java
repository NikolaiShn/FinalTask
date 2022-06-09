package com.dto.mappers;

import com.dto.ProfileKnowledgeDirectoryDto;
import com.model.KnowledgeDirectory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SectionMapper.class, ThemeMapper.class})
public interface ProfileKnowledgeDirectoryMapper {
    ProfileKnowledgeDirectoryMapper INSTANCE = Mappers.getMapper(ProfileKnowledgeDirectoryMapper.class);

    List<ProfileKnowledgeDirectoryDto> knowledgeDirectoriesToProfileKnowledgeDirectoriesDto (List<KnowledgeDirectory> knowledgeDirectories);
    List<KnowledgeDirectory> knowledgeDirectoriesDtoToKnowledgeDirectories (List<ProfileKnowledgeDirectoryDto> profileKnowledgeDirectoriesDto);
}
