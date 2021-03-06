package com.web.controllers;

import com.dto.SectionDto;
import com.exceptions.NotFoundException;
import com.web.services.SectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SectionController {

    private static final Logger sectionControllerLogger = LogManager.getLogger(SectionController.class);

    @Autowired
    private SectionService sectionService;

    /**
     * Метод возврщающий все разделы справочников областей знаний.
     */
    @GetMapping(value = "/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SectionDto> getAllSections() {
        sectionControllerLogger.info("start getAllSections");
        sectionControllerLogger.info("end getAllSections");
        return sectionService.getAllSections();
    }

    /**
     * Метод для создания раздела. Доступен пользователям с ролью ADMIN.
     * @param knowledgeDirectoryName - название справочника области знаний
     * @param sectionName - название раздела
     * @throws NotFoundException - если справочника к которому будет относиться раздел не существует
     */
    @PostMapping(value = "/sections/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean createSection(@RequestParam String knowledgeDirectoryName, @RequestParam String sectionName) throws NotFoundException {
        sectionControllerLogger.info("start createSection");
        sectionControllerLogger.info("end createSection");
        return sectionService.createSection(knowledgeDirectoryName, sectionName);
    }

    /**
     * Метод для изменения имени раздела. Доступен пользователям с ролью ADMIN.
     * Изменяет сразу все имена, если имя раздела одинаковое.
     * @param oldName - текущее название раздела
     * @param newName - новое название раздела
     * @param knowledgeDirectoryName - название справочника области знаний
     * @throws NotFoundException - если такого раздела не существует
     */
    @PutMapping(value = "/sections/editName")
    @ResponseBody
    public boolean editSectionName(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        sectionControllerLogger.info("start editSectionName");
        sectionControllerLogger.info("end editSectionName");
        return sectionService.editSectionName(oldName, newName, knowledgeDirectoryName);
    }

    /**
     * Метод для удаления раздела. Доступен пользователям с ролью ADMIN.
     * Если имя раздела одинаковое удаляет сразу все разделы с таким именем.
     * @param sectionName - название раздела
     * @param knowledgeDirectoryName - название справочника области знаний
     * @throws NotFoundException - если такого раздела не существует
     */
    @DeleteMapping(value = "/sections/delete")
    @ResponseBody
    public boolean deleteSection(@RequestParam("sectionName") String sectionName, @RequestParam("knowledgeDirectoryName") String knowledgeDirectoryName) throws NotFoundException {
        sectionControllerLogger.info("start deleteSection");
        sectionControllerLogger.info("end deleteSection");
        return sectionService.deleteSection(sectionName, knowledgeDirectoryName);
    }
}
