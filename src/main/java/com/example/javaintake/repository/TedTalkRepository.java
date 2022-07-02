package com.example.javaintake.repository;


import com.example.javaintake.domain.entity.TedTalk;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TedTalkRepository extends PagingAndSortingRepository<TedTalk, Long> {
}
