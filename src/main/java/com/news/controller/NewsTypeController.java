package com.news.controller;


import com.news.model.NewsType;
import com.news.model.Response;
import com.news.service.NewsTypeService;
import com.news.util.NewsTypeNotFoundException;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/api/newsType")
@Slf4j
public class NewsTypeController {
    private final NewsTypeService newsTypeService;

    @GetMapping
    public List<NewsType> getAll() {
        return newsTypeService.findAll();
    }

    @GetMapping("/{id}")
    public NewsType getById(@PathVariable("id") Long id) throws NewsTypeNotFoundException {
        return newsTypeService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Response> save( @RequestBody NewsType newsType) {
        NewsType creatingNewsType = newsTypeService.save(newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(creatingNewsType)
                        .message("News type with name " + creatingNewsType.getTypeName() + " is created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody NewsType newsType) {
        NewsType creatingNewsType = newsTypeService.save(newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(creatingNewsType)
                        .message("News type with id " + creatingNewsType.getId() + " is updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        newsTypeService.delete(id);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("News type with id " + id + " was deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
