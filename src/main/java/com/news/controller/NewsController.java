package com.news.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.news.model.News;
import com.news.model.NewsType;
import com.news.model.Response;
import com.news.model.Views;
import com.news.service.NewsService;
import com.news.service.NewsTypeService;
import com.news.util.NewsNotFoundException;
import com.news.util.NewsTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/api/news")
@Slf4j
public class NewsController {
    private final NewsService newsService;
    private final NewsTypeService newsTypeService;

    @GetMapping()
    public List<News> getAll() {
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public News getOneById(@PathVariable("id") Long id) throws NewsNotFoundException {
        return newsService.findOne(id);
    }

    @GetMapping("/newsType/{id}")
    @JsonView(Views.List.class)
    public ResponseEntity<Response> getNewsByNewsType(@PathVariable("id") Long id) {
        NewsType newsType = null;
        try {
            newsType = newsTypeService.findOne(id);
        } catch (NewsTypeNotFoundException e) {
            log.info("News with id " + id + " not found");
        }
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsType.getNewsList())
                        .message("News list with news type " + newsType.getTypeName() + " is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> save(@Valid @RequestBody News news,
                                         @PathVariable("id") Long id ) {  //newsTypeId
        NewsType newsType = null;
        try {
            newsType = newsTypeService.findOne(id);
        } catch (NewsTypeNotFoundException e) {
            log.info("News with id " + id + " not found");
        }
        newsService.save(news, newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("News with name " + news.getName() + " is created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody News news, BindingResult bindingResult,
                                           @PathVariable("id") Long id) throws NewsTypeNotFoundException {
        NewsType newsType = newsTypeService.findOne(id);
        News updatedNews = newsService.save(news, newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(updatedNews)
                        .message("News with id " + updatedNews.getId() + " is updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        News news = null; // This line is not meaningless, because I am checking if the news exists to delete or not
        try {
            news = newsService.findOne(id);
        } catch (NewsNotFoundException e) {
            log.info("News with id " + id + " not found");
        }
        newsService.delete(news.getId());
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("News with id " + id + " was deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}

