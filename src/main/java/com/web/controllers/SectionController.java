package com.web.controllers;

import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.web.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SectionController {

    @Autowired
    private SectionService sectionService;


    @GetMapping(value = "/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SectionDto> getAllSections() {
        return sectionService.getAllSections();
    }

    @PostMapping(value = "/sections/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createSection(@RequestParam String knowledgeDirectoryName, @RequestParam String sectionName) throws NotFoundException {
        sectionService.createSection(knowledgeDirectoryName, sectionName);
    }

    @PutMapping(value = "/sections/editName")
    @ResponseBody
    public boolean editSectionName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        return sectionService.editSectionName(oldName, newName, knowledgeDirectoryName);
    }

    @DeleteMapping(value = "/sections/delete")
    @ResponseBody
    public boolean deleteSection(@RequestParam("sectionName") String sectionName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        return sectionService.deleteSection(sectionName, knowledgeDirectoryName);
    }
}
