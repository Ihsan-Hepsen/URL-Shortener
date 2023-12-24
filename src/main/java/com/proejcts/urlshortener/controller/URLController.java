package com.proejcts.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proejcts.urlshortener.controller.dtos.URLDto;
import com.proejcts.urlshortener.service.URLService;


@RestController
@RequestMapping("/api")
public class URLController {

    private final URLService urlService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(@RequestBody URLDto urlDto) {
        if (urlDto == null || urlDto.url() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var shortURL = urlService.shortenURL(urlDto.url());
        return ResponseEntity.ok(shortURL);
    }

}
