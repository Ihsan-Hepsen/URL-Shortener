package com.projects.urlshortener.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projects.urlshortener.domain.URL;
import com.projects.urlshortener.repository.URLRepository;
import com.projects.utils.ShortCodeGenerator;


@Service
public class URLServiceImpl implements URLService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final URLRepository urlRepository;
    private final String BASE_DOMAIN;

    @Autowired
    public URLServiceImpl(URLRepository urlRepository, @Value("${base_domain}") String BASE_DOMAIN) {
        this.urlRepository = urlRepository;
        this.BASE_DOMAIN = BASE_DOMAIN;
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
    public String shortenURL(String longURL) {
        log.debug("Shortening URL for: " + longURL);
        String shortCode = ShortCodeGenerator.generateShortCode(longURL);
        String shortURL = BASE_DOMAIN + "/" + shortCode;
        
        URL url = new URL(longURL);
        url.setShortCode(shortCode);
        urlRepository.save(url);

        log.debug("Shortening completed: " + shortURL + ". Short code: " + shortCode);
        return shortURL;
    }

    @Override
    public Optional<String> getLongURL(String shortCode) {
        URL url = urlRepository.findByShortCode(shortCode);
        if (url == null) {
            log.debug("No long URL found for the short code: " + shortCode);
            return Optional.empty();
        }
        return Optional.of(url.getLongURL());
    }

}
