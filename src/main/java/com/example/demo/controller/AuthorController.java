package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/authors")
    public ResponseEntity getAuthors() {
        List<Author> authors = service.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity getAuthorById(@PathVariable Long authorId) {
        Optional<Author> author = service.getAuthorByAuthorId(authorId);
        if (author.isPresent()) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/author")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = service.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/author")
    public ResponseEntity<Author> modifyAuthor(@RequestBody Author author) {
        Author savedAuthor = service.saveAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity deleteAuthor(@PathVariable Long authorId) {
        boolean deleted = service.deleteAuthorById(authorId);
        if (deleted) {
            return ResponseEntity.ok(CommonResponse.ok(null));
        }
        return ResponseEntity.badRequest()
                .body(CommonResponse.builder()
                        .code("400")
                        .message("delete failed.")
                        .cause("Author doesn't exist.").build());
    }

}
