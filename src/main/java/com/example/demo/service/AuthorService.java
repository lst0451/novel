package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NovelRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AuthorService {
    private final NovelRepository novelRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public AuthorService(NovelRepository novelRepository,
                         PublisherRepository publisherRepository,
                         AuthorRepository authorRepository) {
        this.novelRepository = novelRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorByNovelName(String novelName) {
        Optional<Novel> novelInDb = novelRepository.findByName(novelName);
        Author author = null;
        if (novelInDb.isPresent()) {
            author = authorRepository.findAuthorByNovelListContains(novelInDb.get());
        }
        return Optional.ofNullable(author);
    }

    public Author getAuthorByNovel(Novel novel) {
        return authorRepository.findAuthorByNovelListContains(novel);
    }

    public Optional<Author> getAuthorByAuthorId(Long authorId) {
        return authorRepository.findById(authorId);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public boolean deleteAuthorById(Long authorId) {
        try {
            authorRepository.deleteById(authorId);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
