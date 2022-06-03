package com.dto.mappers;

import com.dto.SectionDto;
import com.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    @Mapping(target = "knowledgeDirectory", ignore = true)
    SectionDto sectionToSectionDto (Section section);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    Section sectionDtoToSection (SectionDto sectionDto);

    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<SectionDto> sectionsToSectionDtos (List<Section> sections);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<Section> sectionDtosToSections (List<SectionDto> sectionDtos);
}
