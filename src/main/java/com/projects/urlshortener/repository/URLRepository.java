package com.projects.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.urlshortener.domain.URL;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    URL findByShortCode(String shortCode);   
}
