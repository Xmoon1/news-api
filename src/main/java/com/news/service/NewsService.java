package com.news.service;

import com.news.model.News;
import com.news.model.NewsType;
import com.news.repository.NewsRepository;
import com.news.util.NewsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository repository;

    public List<News> findAll(){
        return repository.findAll();
    }
    public News findOne(Long id) throws NewsNotFoundException {
        Optional<News> foundedNews = repository.findById(id);
        return foundedNews.orElseThrow(() -> new NewsNotFoundException("News with id " + id +" is not found."));
    }
    @Transactional
    public News save(News news, NewsType newsType){
        news.setNewsType(newsType);
        repository.save(news);
        return news;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
