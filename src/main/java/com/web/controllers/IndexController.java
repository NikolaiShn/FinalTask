package com.web.controllers;

import com.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping(value = {"/", "/maincheck"})
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/maincheckb", produces = MediaType.APPLICATION_JSON_VALUE)
    public User indexPages() {
//        List<User> list = new ArrayList<>();
//        list.add(new User("Vladimir"));
//        list.add(new User("Vlsds"));
//        list.add(new User("Vlsdsdadimir"));
        return new User("John");
    }
}
