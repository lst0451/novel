package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Novel;
import com.example.demo.entity.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NovelRepository;
import com.example.demo.repository.PublisherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class ImportService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private NovelRepository novelRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * Import novel,author,publisher information.
     *
     * @param file  text file containing novels information.
     * @param model for UI display.
     */
    public void importNovelInfo(MultipartFile file, Model model) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            line = bufferedReader.readLine();
            Map<String, String> temp = new HashMap<>();
            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                    processLine(line, temp);
                } else {
                    saveInfo(temp);
                    temp = new HashMap<>();
                }
                line = bufferedReader.readLine();
            }
            model.addAttribute("message", "success");
        } catch (IOException e) {
            log.error("import error:" + e.getMessage());
            model.addAttribute("message", "failed.");
            e.printStackTrace();
        }
    }

    private void saveInfo(Map<String, String> temp) {
        Novel novel = new Novel();
        Author author = new Author();
        Publisher publisher = new Publisher();

//        To avoid duplicate data.
        for (Map.Entry<String, String> entry : temp.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (k.equals("Name")) {
                Optional<Novel> novelInDb = novelRepository.findByName(v.trim());
                if (!novelInDb.isPresent()) {
                    novel.setName(v.trim());
                } else {
                    novel = novelInDb.get();
                }
            } else if (k.equals("Author")) {
                Optional<Author> authorInDb = authorRepository.findByAuthorName(v.trim());
                if (!authorInDb.isPresent()) {
                    author.setAuthorName(v.trim());
                } else {
                    author = authorInDb.get();
                }
            } else if (k.equals("Publisher")) {
                Optional<Publisher> publisherInDb = publisherRepository.findByName(v.trim());
                if (!publisherInDb.isPresent()) {
                    publisher.setName(v.trim());
                } else {
                    publisher = publisherInDb.get();
                }
            } else {
                continue;
            }
        }
        for (Map.Entry<String, String> entry : temp.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            switch (k.trim()) {
                case "Genre":
                    novel.setGenre(v.trim());
                    break;
                case "Pages":
                    novel.setPages(Integer.valueOf(v.trim()));
                    break;
                case "Language":
                    novel.setLanguage(v.trim());
                    break;
                case "Price":
                    novel.setPrice(new BigDecimal(v.trim()));
                    break;
                case "ISBN":
                    novel.setIsbn(v.trim());
                    break;
                case "Product Dimensions":
                    novel.setProductDimensions(v.trim());
                    break;
                case "Weight":
                    novel.setWeight(Integer.valueOf(v.trim()));
                    break;
                case "Publish Date":
                    LocalDate date = LocalDate.parse(v.trim());
                    novel.setPublishDate(date);
                    break;
                case "Remark":
                    novel.setRemark(v.trim());
                    break;
                case "Introduce":
                    author.setIntroduce(v.trim());
                    break;
            }
//            author = authorRepository.save(author);
            novel.setAuthor(author);
//            publisher = publisherRepository.save(publisher);
            novel.setPublisher(publisher);
            novelRepository.save(novel);
        }
    }

    private void processLine(String line, Map temp) {
        String[] split = line.split(":");
        if (split.length != 2) {
            log.error("line info error:" + line);
            return;
        }
        temp.put(split[0], split[1]);
    }
}
