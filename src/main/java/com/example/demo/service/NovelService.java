package com.example.demo.service;

import com.example.demo.common.CommonResponse;
import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import com.example.demo.entity.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NovelRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
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

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Novel> getAllNovels() {
        return novelRepository.findAll();
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Novel getNovelByNovelName(String novelName) {
        return novelRepository.findByName(novelName).get();
    }

    public Optional<Novel> getNovelById(Long novelId) {
        return novelRepository.findById(novelId);
    }

    public List<Novel> getNovelsByPublisher(Long publisherId) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherId);
        return novelRepository.findAllByPublisher(publisher);
    }

    public CommonResponse addNovel(Novel novel) {
        Author author = novel.getAuthor();
        Publisher publisher = novel.getPublisher();
        CommonResponse.CommonResponseBuilder<Object> builder = CommonResponse.builder();
        if (author == null) {
            return builder
                    .code("400")
                    .message("Author is null")
                    .body(null).build();
        }
        if (publisher == null) {
            return builder
                    .code("400")
                    .message("publisher is null")
                    .body(null).build();
        }
        if (author.getId() != null) {
            Optional<Author> byId = authorRepository.findById(author.getId());
            if (byId.isPresent()) {
                author = byId.get();
                novel.setAuthor(author);
            } else {
                builder.code("400")
                        .message("Author doesn't exist,id=" + author.getId())
                        .build();
            }
        }
        if (publisher.getId() != null) {
            Optional<Publisher> byId = publisherRepository.findById(publisher.getId());
            if (byId.isPresent()) {
                publisher = byId.get();
                novel.setPublisher(publisher);
            } else {
                builder.code("400")
                        .message("Author doesn't exist,id=" + publisher.getId())
                        .build();
            }
        }
        Novel save = novelRepository.save(novel);
        return CommonResponse.ok(save);
    }

    public Novel saveNovel(Novel novel) {
        return novelRepository.save(novel);
    }

    public void deleteNovel(Long novelId) {
        novelRepository.deleteById(novelId);
    }
}
