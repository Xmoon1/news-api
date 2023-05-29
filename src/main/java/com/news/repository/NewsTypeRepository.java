package com.news.repository;

import com.news.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
}
