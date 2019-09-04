package com.example.demo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log4j2
public class ImportService {
    public void importNovelInfo(MultipartFile file, Model model) {
        try {
            byte[] bytes = file.getBytes();
            //Todo here will be the core business logic.
//            Path path = Paths.get("E:\\fileUpload/" + file.getOriginalFilename());
//            Files.write(path, bytes);
            model.addAttribute("message", "succes");
        } catch (IOException e) {
            log.error("import error:" + e.getMessage());
            model.addAttribute("message", "failed.");
            e.printStackTrace();
        }
    }
}
