package com.proejcts.urlshortener.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proejcts.urlshortener.domain.URL;
import com.proejcts.urlshortener.repository.URLRepository;

@Service
public class URLServiceImpl implements URLService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final URLRepository urlRepository;

    @Autowired
    public URLServiceImpl(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Optional<URL> getURLById(Long id) {
        return urlRepository.findById(id);
    }

    @Override
    public List<URL> getAllURLs() {
        return urlRepository.findAll();
    }

    @Override
    public String shortenURL(String url) {
        return "";
    }

    @Override
    public Optional<String> getLongURL(String shortCode) {
        String longURL = urlRepository.findByShortURL(shortCode);
        if (longURL == null) {
            log.debug("No long URL found for the short code: " + shortCode);
            return Optional.empty();
        }
        return Optional.of(longURL);
    }

}