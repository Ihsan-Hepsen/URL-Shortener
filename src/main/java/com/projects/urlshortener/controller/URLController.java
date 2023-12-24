package com.projects.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.urlshortener.controller.dtos.URLDto;
import com.projects.urlshortener.service.URLService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class URLController {

    private final URLService urlService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    
    @PostMapping("/api/shorten")
    public ResponseEntity<String> shortenURL(@RequestParam("url") String url) {
        if (url == null || url.length() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Shortening request for URL: " + url);
        var shortURL = urlService.shortenURL(url);
        return ResponseEntity.ok(shortURL);
    }
    
    // @PostMapping("/api/shorten")
    // public ResponseEntity<String> shortenURL(@RequestBody URLDto urlDto) {
    //     if (urlDto == null || urlDto.url() == null) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }
    //     log.info("Shortening request for URL: " + urlDto.url());
    //     var shortURL = urlService.shortenURL(urlDto.url());
    //     return ResponseEntity.ok(shortURL);
    // }

    @GetMapping("/{short_code}")
    public ResponseEntity<Void> redirect(@PathVariable("short_code") String shortCode, HttpServletResponse httpServletResponse) {
        if (shortCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Received request for the long URL of short code: " + shortCode);
        var longURL = urlService.getLongURL(shortCode);
        if (longURL.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Redirecting to " + longURL.get() + " for short code: " + shortCode);
        httpServletResponse.setHeader("Location", longURL.get());
        httpServletResponse.setStatus(301);
        return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
    }

}
