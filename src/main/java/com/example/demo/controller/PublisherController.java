package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.entity.Publisher;
import com.example.demo.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    public ResponseEntity getPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity getPublisherById(@PathVariable Long publisherId) {
        Optional<Publisher> publisher = publisherService.getPublisherByPublisherId(publisherId);
        if (publisher.isPresent()) {
            return ResponseEntity.ok(publisher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/publisher")
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        Publisher savedAuthor = publisherService.savePublisher(publisher);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/publisher")
    public ResponseEntity<Publisher> modifyPublisher(@RequestBody Publisher publisher) {
        Publisher savedPublisher = publisherService.savePublisher(publisher);
        return ResponseEntity.ok(savedPublisher);
    }

    @DeleteMapping("/author/{publisherId}")
    public ResponseEntity deletePublisher(@PathVariable Long publisherId) {
        boolean deleted = publisherService.deletePublisherById(publisherId);
        if (deleted) {
            return ResponseEntity.ok(CommonResponse.ok(null));
        }
        return ResponseEntity.badRequest()
                .body(CommonResponse.builder()
                        .code("400")
                        .message("delete failed.")
                        .cause("Publisher doesn't exist.").build());
    }


}
