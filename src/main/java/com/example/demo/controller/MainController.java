package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import com.example.demo.entity.Publisher;
import com.example.demo.service.AuthorService;
import com.example.demo.service.ImportService;
import com.example.demo.service.NovelService;
import com.example.demo.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@Controller
public class MainController {

    private final NovelService novelService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ImportService importService;

    public MainController(NovelService novelService,
                          AuthorService authorService,
                          PublisherService publisherService,
                          ImportService importService) {
        this.novelService = novelService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.importService = importService;
    }

    @GetMapping("/")
    @ApiIgnore
    public String home() {
        return "index";
    }

    @GetMapping("/upload")
    public String getUploadPage(Model model) {
        return "upload";
    }

    @PostMapping("/import")
    public String importText(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "The file is empty!");
            return "/importStatus";
        }
        importService.importNovelInfo(file, model);
        return "importStatus";
    }

    @GetMapping("/novels")
    public String getAllNovels(Model model) {
        model.addAttribute("novels", novelService.getAllNovels());
        return "novelList";
    }

    @GetMapping("/novel/{novelId}")
    public String getNovelDetailsById(@PathVariable Long novelId, Model model) {
        Optional<Novel> novelById = novelService.getNovelById(novelId);
        if (novelById.isPresent()) {
            model.addAttribute("novel", novelById.get());
        } else {
            return "index";
        }
        return "novelDetails";
    }

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", novelService.getAllAuthors());
        return "authorList";
    }

    @GetMapping("/author/{authorId}")
    public String getAuthorDetailsById(@PathVariable Long authorId, Model model) {
        Optional<Author> authorByAuthorId = authorService.getAuthorByAuthorId(authorId);
        if (authorByAuthorId.isPresent()) {
            model.addAttribute("author", authorByAuthorId.get());
        } else {
            return "index";
        }
        return "authorDetails";
    }

    @GetMapping("/publishers")
    public String getAllPublishers(Model model) {
        model.addAttribute("publishers", novelService.getAllPublishers());
        return "publisherList";
    }

    @GetMapping("/publisher/{publisherId}")
    public String getPublisherDetails(@PathVariable Long publisherId, Model model) {
        Optional<Publisher> publisherByPublisherId =
                publisherService.getPublisherByPublisherId(publisherId);
        if (publisherByPublisherId.isPresent()) {
            model.addAttribute("publisher", publisherByPublisherId.get()
            );
        } else {
            return "index";
        }
        return "publisherDetails";
    }

    @GetMapping("/publisher_novels/{publisherId}")
    public String getPublisherNovelList(@PathVariable Long publisherId, Model model) {
        model.addAttribute("novels",
                novelService.getNovelsByPublisher(publisherId));
        return "novelList";
    }
}
