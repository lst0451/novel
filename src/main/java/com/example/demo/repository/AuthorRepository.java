package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select a from Author a where a.authorName=?1")
    Optional<Author> findByAuthorName(String name);

    Author findAuthorByNovelListContains(Novel novel);

}
