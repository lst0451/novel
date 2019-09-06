package com.example.demo.service;

import com.example.demo.entity.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NovelRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PublisherService {
    private final NovelRepository novelRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public PublisherService(NovelRepository novelRepository,
                            PublisherRepository publisherRepository,
                            AuthorRepository authorRepository) {
        this.novelRepository = novelRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Publisher> getPublisherByPublisherId(Long publisherId) {
        return publisherRepository.findById(publisherId);
    }


    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public boolean deletePublisherById(Long publisherId) {
        try {
            publisherRepository.deleteById(publisherId);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
