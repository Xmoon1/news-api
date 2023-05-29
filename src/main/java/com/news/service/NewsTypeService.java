package com.news.service;
import com.news.model.NewsType;
import com.news.repository.NewsTypeRepository;
import com.news.util.NewsTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsTypeService {
    private final NewsTypeRepository repository;

    public List<NewsType> findAll(){
        return repository.findAll();
    }

    public NewsType findOne(Long id) throws NewsTypeNotFoundException {
        Optional<NewsType> foundedNewsType =  repository.findById(id);
        return foundedNewsType.orElseThrow(() -> new NewsTypeNotFoundException("News type with id " + id + " is not found."));
    }

    @Transactional
    public NewsType save(NewsType newsType){
        repository.save(newsType);
        return newsType;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}













