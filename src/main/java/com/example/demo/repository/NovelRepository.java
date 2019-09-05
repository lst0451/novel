package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {

    Optional<Novel> findByName(String name);

    List<Novel> findAllByAuthor(Author author);
}
