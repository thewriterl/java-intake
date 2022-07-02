package com.example.javaintake.service;

import com.example.javaintake.domain.dto.TedTalkDTO;
import org.springframework.data.domain.Page;

public interface TedTalkService {

    TedTalkDTO createTedTalk(TedTalkDTO dto);
    Page<TedTalkDTO> getAll(Integer page);
    Page<TedTalkDTO> search(String author, String title, Long views, Long likes, Integer page);
    void delete(Long id);
}
