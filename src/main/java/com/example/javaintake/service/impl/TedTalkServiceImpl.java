package com.example.javaintake.service.impl;

import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.domain.entity.TedTalk;
import com.example.javaintake.repository.TedTalkRepository;
import com.example.javaintake.service.TedTalkService;
import com.example.javaintake.utils.components.TedTalkUtils;
import org.springframework.stereotype.Service;

@Service
public class TedTalkServiceImpl implements TedTalkService {

    private final TedTalkRepository tedTalkRepository;

    public TedTalkServiceImpl(TedTalkRepository tedTalkRepository) {
        this.tedTalkRepository = tedTalkRepository;
    }

    @Override
    public TedTalkDTO createTedTalk(TedTalkDTO dto) {
        TedTalkUtils.validateCreate(dto);
        TedTalk tedTalk = new TedTalk(dto);
        tedTalk.setDeleted(false);
        tedTalk.setLink(TedTalkUtils.generateLink(dto.getAuthor(), dto.getTitle()));
        return new TedTalkDTO(this.tedTalkRepository.save(tedTalk));
    }
}
