package com.dto.mappers;

import com.dto.ThemeDto;
import com.model.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(target = "knowledgeDirectory", ignore = true)
    ThemeDto themeToThemeDto(Theme theme);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    Theme themeDtoToTheme(ThemeDto themeDto);

    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<ThemeDto> themesToThemeDtos(List<Theme> themes);
    @Mapping(target = "knowledgeDirectory", ignore = true)
    List<Theme> themeDtosToThemes(List<ThemeDto> themeDtos);
}
