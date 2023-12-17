package com.proejcts.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proejcts.urlshortener.domain.URL;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    String findByShortURL(String shortCode);   
}
