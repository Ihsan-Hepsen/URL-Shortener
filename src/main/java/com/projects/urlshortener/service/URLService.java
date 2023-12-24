package com.projects.urlshortener.service;

import java.util.Optional;

import com.projects.urlshortener.domain.URL;

import java.util.List;


public interface URLService {
    Optional<URL> getURLById(Long id);
    List<URL> getAllURLs();
    String shortenURL(String url);
    Optional<String> getLongURL(String shortCode);
}
