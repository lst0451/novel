package com.example.demo.controller;

import com.example.demo.entity.Novel;
import com.example.demo.service.NovelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NovelController {

    private final NovelService service;

    public NovelController(NovelService service) {
        this.service = service;
    }

    @GetMapping("/author_novels/{authorName}")
    public ResponseEntity<?> getNovelListByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(service.getNovelsByAuthor(authorName));
    }

    @GetMapping("/novels")
    public ResponseEntity getAllNovels() {
        List<Novel> allNovels = service.getAllNovels();
        return ResponseEntity.ok(allNovels);
    }

    @GetMapping("/novel/{novelId}")
    public ResponseEntity<Novel> getNovel(@PathVariable Long novelId) {
        Optional<Novel> novelById = service.getNovelById(novelId);
        if (novelById.isPresent()) {
            return ResponseEntity.ok(novelById.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/novel")
    public ResponseEntity addNovel(@RequestBody Novel novel) {
        return ResponseEntity.ok(service.addNovel(novel));
    }

    @PutMapping("/novel")
    public ResponseEntity<Novel> modifyNovel(@RequestBody Novel novel) {
        Novel saveNovel = service.saveNovel(novel);
        return ResponseEntity.ok(saveNovel);
    }

    @DeleteMapping("/novel/{novelId}")
    public ResponseEntity<Novel> deleteNovel(@PathVariable Long novelId) {
        service.deleteNovel(novelId);
        return ResponseEntity.ok(null);
    }

}
