package com.web.controllers;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.exceptions.SectionExistException;
import com.exceptions.ThemeExistException;
import com.web.services.KnowledgeDirectoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KnowledgeDirectoryController {

    private static final Logger knowledgeDirectoryControllerControllerLogger = LogManager.getLogger(KnowledgeDirectoryController.class);

    @Autowired
    private KnowledgeDirectoryService knowledgeDirectoryService;

    @GetMapping(value = "/knowledgeDirectories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<KnowledgeDirectoryDto> getAllKnowledgeDirectories() {
        knowledgeDirectoryControllerControllerLogger.info("start getAllKnowledgeDirectories");
        knowledgeDirectoryControllerControllerLogger.info("end getAllKnowledgeDirectories");
        return knowledgeDirectoryService.getAllKnowledgeDirectories();
    }

    @GetMapping(value = "/knowledgeDirectory/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ThemeDto> getAllThemesKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        knowledgeDirectoryControllerControllerLogger.info("start getAllThemesKnowledgeDirectories");
        knowledgeDirectoryControllerControllerLogger.info("end getAllThemesKnowledgeDirectories");
        return knowledgeDirectoryService.findKnowledgeDirectoryThemes(knowledgeDirectoryName);
    }

    @GetMapping(value = "/knowledgeDirectory/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SectionDto> getAllSectionsKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        knowledgeDirectoryControllerControllerLogger.info("start getAllSectionsKnowledgeDirectories");
        knowledgeDirectoryControllerControllerLogger.info("end getAllSectionsKnowledgeDirectories");
        return knowledgeDirectoryService.findKnowledgeDirectorySections(knowledgeDirectoryName);
    }

    @PostMapping(value = "/knowledgeDirectory/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createKnowledgeDirectory(@RequestParam String knowledgeDirectoryName) {
        knowledgeDirectoryControllerControllerLogger.info("start createKnowledgeDirectory");
        knowledgeDirectoryControllerControllerLogger.info("end createKnowledgeDirectory");
        return knowledgeDirectoryService.createKnowLedgeDirectory(knowledgeDirectoryName);
    }

    @PutMapping(value = "/knowledgeDirectory/editName")
    @ResponseBody
    public boolean editKnowledgeDirectory(@RequestParam String oldName, @RequestParam String newName) throws NotFoundException {
        knowledgeDirectoryControllerControllerLogger.info("start editKnowledgeDirectory");
        knowledgeDirectoryControllerControllerLogger.info("end editKnowledgeDirectory");
        return knowledgeDirectoryService.editKnowledgeDirectoryName(newName, oldName);
    }

    @DeleteMapping(value = "/knowledgeDirectory/delete")
    @ResponseBody
    public boolean deleteKnowledgeDirectory(@RequestParam String knowledgeDirectoryName) throws NotFoundException {
        knowledgeDirectoryControllerControllerLogger.info("start deleteKnowledgeDirectory");
        knowledgeDirectoryControllerControllerLogger.info("end deleteKnowledgeDirectory");
        return knowledgeDirectoryService.deleteKnowledgeDirectory(knowledgeDirectoryName);
    }

    @PostMapping(value = "/knowledgeDirectory/createTheme", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createTheme(@RequestParam String knowledgeDirectoryName, @RequestParam String themeName) throws NotFoundException, ThemeExistException {
        knowledgeDirectoryControllerControllerLogger.info("start createTheme");
        knowledgeDirectoryControllerControllerLogger.info("end createTheme");
        return knowledgeDirectoryService.addTheme(knowledgeDirectoryName, themeName);
    }

    @PostMapping(value = "/knowledgeDirectory/createSection", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createSection(@RequestParam String knowledgeDirectoryName, @RequestParam String sectionName) throws NotFoundException, SectionExistException {
        knowledgeDirectoryControllerControllerLogger.info("start createSection");
        knowledgeDirectoryControllerControllerLogger.info("end createSection");
        return knowledgeDirectoryService.addSection(knowledgeDirectoryName, sectionName);
    }
}
