package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MainController {

    @GetMapping("/novel/{authorName}")
    public ResponseEntity<?> getNovelListByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(Arrays.asList("The Lord of the Ring", "The Hobbit"));
    }
}
