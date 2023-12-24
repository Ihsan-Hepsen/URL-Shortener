package com.projects.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping({ "", "/", "/home" })
    public String displayIndexPage() {
        log.info("Index page is on display.");
        return "index";
    }
}
