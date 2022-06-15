package com.web.controllers;

import com.dto.ProfileDto;
import com.web.services.ProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    private static final Logger profileControllerLogger = LogManager.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProfileDto getProfile() {
        profileControllerLogger.info("start getProfile");
        profileControllerLogger.info("end getProfile");
        return profileService.getProfile();
    }

    @PutMapping("/profile/changeName")
    @ResponseBody
    public boolean changeProfileUserName(@RequestParam String newName) {
        profileControllerLogger.info("start changeProfileUserName");
        profileControllerLogger.info("end changeProfileUserName");
        return profileService.changeProfileUserName(newName);
    }

    @PutMapping("/profile/changeLastName")
    @ResponseBody
    public boolean changeProfileUserLastName(@RequestParam String newLastName) {
        profileControllerLogger.info("start changeProfileUserLastName");
        profileControllerLogger.info("end changeProfileUserLastName");
        return profileService.changeProfileUserLastName(newLastName);
    }
}
