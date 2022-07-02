package com.example.javaintake.repository;


import com.example.javaintake.domain.entity.TedTalk;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TedTalkRepository extends PagingAndSortingRepository<TedTalk, Long>, QueryByExampleExecutor<TedTalk> {
}
