package com.web.controllers;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.web.services.KnowledgeDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KnowledgeDirectoryController {

    @Autowired
    private KnowledgeDirectoryService knowledgeDirectoryService;

    @GetMapping(value = "/knowledgeDirectories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<KnowledgeDirectoryDto> getAllKnowledgeDirectories() {
        return knowledgeDirectoryService.getAllKnowledgeDirectories();
    }

    @GetMapping(value = "/knowledgeDirectory/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ThemeDto> getAllThemesKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        return knowledgeDirectoryService.findKnowledgeDirectoryThemes(knowledgeDirectoryName);
    }

    @GetMapping(value = "/knowledgeDirectory/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SectionDto> getAllSectionsKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        return knowledgeDirectoryService.findKnowledgeDirectorySections(knowledgeDirectoryName);
    }

    @PostMapping(value = "/knowledgeDirectory/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createKnowledgeDirectory(@RequestParam String knowledgeDirectoryName) {
        return knowledgeDirectoryService.createKnowLedgeDirectory(knowledgeDirectoryName);
    }

    @PutMapping(value = "/knowledgeDirectory/editName")
    @ResponseBody
    public boolean editKnowledgeDirectory(@RequestParam String oldName, @RequestParam String newName) throws NotFoundException {
        return knowledgeDirectoryService.editKnowledgeDirectoryName(newName, oldName);
    }

    @DeleteMapping(value = "/knowledgeDirectory/delete")
    @ResponseBody
    public boolean deleteKnowledgeDirectory(@RequestParam String knowledgeDirectoryName) throws NotFoundException {
        return knowledgeDirectoryService.deleteKnowledgeDirectory(knowledgeDirectoryName);
    }

    //запретить не админу
    @PostMapping(value = "/knowledgeDirectory/createTheme", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createTheme(@RequestParam String knowledgeDirectoryName, @RequestParam String themeName) throws NotFoundException {
        return knowledgeDirectoryService.addTheme(knowledgeDirectoryName, themeName);
    }
    //запретить не админу
    @PostMapping(value = "/knowledgeDirectory/createSection", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createSection(@RequestParam String knowledgeDirectoryName, @RequestParam String sectionName) throws NotFoundException {
        return knowledgeDirectoryService.addSection(knowledgeDirectoryName, sectionName);
    }

}
