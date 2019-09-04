package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@RestController
public class MainController {

    @GetMapping("/novel/{authorName}")
    public ResponseEntity<?> getNovelListByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(Arrays.asList("The Lord of the Ring", "The Hobbit"));
    }

}
