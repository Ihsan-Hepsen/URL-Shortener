package com.proejcts.urlshortener.service;

import java.util.Optional;
import java.util.List;
import com.proejcts.urlshortener.domain.URL;


public interface URLService {
    Optional<URL> getURLById(Long id);
    List<URL> getAllURLs();
    String shortenURL(String url);
    Optional<String> getLongURL(String shortCode);
}
