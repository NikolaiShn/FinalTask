package com.dto.mappers;

import com.dto.ThemeDto;
import com.model.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ThemeMapper {
    ThemeMapper INSTANCE = Mappers.getMapper(ThemeMapper.class);

    @Mapping(target = "knowledgeDirectory", ignore = true)
    ThemeDto themeToThemeDto(Theme theme);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    Theme themeDtoToTheme(ThemeDto themeDto);

    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<ThemeDto> themesToThemeDtos(List<Theme> themes);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<Theme> themeDtosToThemes(List<ThemeDto> themeDtos);
}
