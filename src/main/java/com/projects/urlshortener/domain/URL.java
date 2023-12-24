package com.projects.urlshortener.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class URL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longURL;
    @Column(unique = true)
    private String shortCode;

    public URL() {
        this.longURL = null;
        this.shortCode = null;
    }

    public URL(String url) {
        this.longURL = url;
        this.shortCode = null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortURL) {
        this.shortCode = shortURL;
    }

    
    
}
