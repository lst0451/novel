package com.example.demo.controller;

import com.example.demo.service.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @GetMapping("/upload")
    public String upload(Model model) {
        return "/upload";
    }

    @PostMapping("/import")
    public String importNovel(@RequestParam("file") MultipartFile file, Model model) {

        if (file.isEmpty()) {
            model.addAttribute("message", "The file is empty!");
            return "/importStatus";
        }
//        try {
            importService.importNovelInfo(file, model);
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("E:\\fileUpload/" + file.getOriginalFilename());
//            Files.write(path, bytes);
//            model.addAttribute("message", "succes");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "/importStatus";
    }
}
