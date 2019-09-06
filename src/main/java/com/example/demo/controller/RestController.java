package com.example.demo.controller;

import com.example.demo.common.NovelReq;
import com.example.demo.entity.Author;
import com.example.demo.service.NovelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class RestController {

    private final NovelService service;

    public RestController(NovelService service) {
        this.service = service;
    }

    @GetMapping("/author_novels/{authorName}")
    public ResponseEntity<?> getNovelListByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(service.getNovelsByAuthor(authorName));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> getAuthorByNovel(@RequestBody NovelReq req) {
        String name = req.getName();
        Optional<Author> author = service.getAuthorByNovelName(name);
        if (author.isPresent()) {
            return ResponseEntity.ok().body(author.get());
        }
        return ResponseEntity.notFound().build();
    }

}
