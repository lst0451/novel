package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.service.ImportService;
import com.example.demo.service.NovelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    private final NovelService service;
    private final ImportService importService;

    public MainController(NovelService service, ImportService importService) {
        this.service = service;
        this.importService = importService;
    }

    @GetMapping("/")
    @ApiIgnore
    public String home() {
        return "index";
    }

    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";
    }

    @PostMapping("/import")
    public String importNovel(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "The file is empty!");
            return "/importStatus";
        }
        importService.importNovelInfo(file, model);
        return "importStatus";
    }

    @GetMapping("/authors")
    public String authorList(Model model) {
        model.addAttribute("authors", service.getAllAuthors());
        return "authorList";
    }

    @GetMapping("/novels")
    public String novelList(Model model) {
        model.addAttribute("novels", service.getAllNovels());
        return "novelList";
    }

    @GetMapping("/publishers")
    public String publisherList(Model model) {
        model.addAttribute("publishers", service.getAllPublishers());
        return "publisherList";
    }
}
