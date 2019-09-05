package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import com.example.demo.entity.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NovelRepository;
import com.example.demo.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NovelService {
    private final NovelRepository novelRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public NovelService(NovelRepository novelRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.novelRepository = novelRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    public List<Novel> getNovelsByAuthor(String authorName) {
        Optional<Author> author = authorRepository.findByAuthorName(authorName);
        List<Novel> novelList = new ArrayList<>();
        if (author.isPresent()) {
            novelList.addAll(novelRepository.findAllByAuthor(author.get()));
        }
        return novelList;
    }

    public Optional<Author> getAuthorByNovelName(String novelName){
        Optional<Novel> novelInDb = novelRepository.findByName(novelName);
        Author author=null;
        if (novelInDb.isPresent()){
            author = authorRepository.findAuthorByNovelListContains(novelInDb.get());
        }
        return Optional.ofNullable(author);
    }

    public Author getAuthorByNovel(Novel novel){
        return authorRepository.findAuthorByNovelListContains(novel);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Novel> getAllNovels(){
        return novelRepository.findAll();
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
}
