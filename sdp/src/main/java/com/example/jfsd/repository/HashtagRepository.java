package com.example.jfsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jfsd.model.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    // Find hashtag by text
    Hashtag findByText(String text);
}