package com.web.controllers;

import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.web.services.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ThemeController {

    private static final Logger themeControllerLogger = LogManager.getLogger(ThemeController.class);

    @Autowired
    private ThemeService themeService;

    @GetMapping(value = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ThemeDto> getAllThemes() {
        themeControllerLogger.info("start getAllThemes");
        themeControllerLogger.info("end getAllThemes");
        return themeService.getAllThemes();
    }

    @PostMapping(value = "/themes/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createTheme(@RequestParam String knowledgeDirectoryName, @RequestParam String themeName) throws NotFoundException {
        themeControllerLogger.info("start createTheme");
        themeControllerLogger.info("end createTheme");
        return themeService.createTheme(knowledgeDirectoryName, themeName);
    }

    @PutMapping(value = "/themes/editName")
    @ResponseBody
    public boolean editThemeName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        themeControllerLogger.info("start editThemeName");
        themeControllerLogger.info("end editThemeName");
        return themeService.editThemeName(oldName, newName, knowledgeDirectoryName);
    }

    @DeleteMapping(value = "/themes/delete")
    @ResponseBody
    public boolean deleteTheme(@RequestParam String themeName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        themeControllerLogger.info("start deleteTheme");
        themeControllerLogger.info("end deleteTheme");
        return themeService.deleteTheme(themeName, knowledgeDirectoryName);
    }
}
