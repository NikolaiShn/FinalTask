package com.web.controllers;

import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.web.services.KnowledgeDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ThemeDto> getAllThemesKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        return knowledgeDirectoryService.findKnowledgeDirectoryThemes(knowledgeDirectoryName);
    }

    @GetMapping(value = "/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SectionDto> getAllSectionsKnowledgeDirectories(@RequestParam String knowledgeDirectoryName) {
        return knowledgeDirectoryService.findKnowledgeDirectorySections(knowledgeDirectoryName);
    }

}
