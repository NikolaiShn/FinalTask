package com.web.controllers;

import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.web.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping(value = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ThemeDto> getAllThemes() {
        return themeService.getAllThemes();
    }

    @PostMapping(value = "/themes/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createTheme(@RequestParam String knowledgeDirectoryName, @RequestParam String themeName) throws NotFoundException {
        return themeService.createTheme(knowledgeDirectoryName, themeName);
    }

    @PutMapping(value = "/themes/editName")
    @ResponseBody
    public boolean editThemeName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName) throws NotFoundException {
        return themeService.editThemeName(oldName, newName);
    }

    @DeleteMapping(value = "/themes/delete")
    @ResponseBody
    public boolean deleteTheme(@RequestParam String themeName) throws NotFoundException {
        return themeService.deleteTheme(themeName);
    }
}
