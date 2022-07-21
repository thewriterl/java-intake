package com.example.javaintake.service.impl;

import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.domain.entity.TedTalk;
import com.example.javaintake.repository.TedTalkRepository;
import com.example.javaintake.service.TedTalkService;
import com.example.javaintake.utils.DateUtils;
import com.example.javaintake.utils.components.TedTalkUtils;
import com.example.javaintake.utils.exception.TedTalkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TedTalkServiceImpl implements TedTalkService {

    private final TedTalkRepository tedTalkRepository;

    private final Logger logger = LoggerFactory.getLogger(TedTalkServiceImpl.class);

    public TedTalkServiceImpl(TedTalkRepository tedTalkRepository) {
        this.tedTalkRepository = tedTalkRepository;
    }

    @Override
    @Transactional
    public TedTalkDTO createTedTalk(TedTalkDTO dto) {
        TedTalkUtils.validateCreate(dto);
        logger.debug("Did validate new Ted Talk");
        TedTalk tedTalk = new TedTalk(dto);
        tedTalk.setDeleted(false);
        tedTalk.setLink(TedTalkUtils.generateLink(dto.getAuthor(), dto.getTitle()));
        return new TedTalkDTO(this.tedTalkRepository.save(tedTalk));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TedTalkDTO> getAll(Integer page) {
        return tedTalkRepository.findAll(PageRequest.of(page == null ? 0 : page, 50)).map(TedTalkDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TedTalkDTO> search(String author, String title, Long views, Long likes, Integer page) {
        TedTalk tedTalk = new TedTalk();
        tedTalk.setAuthor(author);
        tedTalk.setTitle(title);
        tedTalk.setViews(views);
        tedTalk.setLikes(likes);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();

        if (author != null && !author.isBlank() && !author.isEmpty()) {
            exampleMatcher.withMatcher("author", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        }

        if (title != null && !title.isBlank() && !title.isEmpty()) {
            exampleMatcher.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        }

        if (views != null) {
            exampleMatcher.withMatcher("views", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        if (likes != null) {
            exampleMatcher.withMatcher("likes", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        Example<TedTalk> tedTalkExample = Example.of(tedTalk, exampleMatcher);
        return this.tedTalkRepository.findAll(tedTalkExample, PageRequest.of(page == null ? 0 : page, 50)).map(TedTalkDTO::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TedTalk tedTalk = this.tedTalkRepository.findById(id).orElseThrow(() -> new TedTalkException("TED Talk Not Found!", HttpStatus.NOT_FOUND));
        if (Boolean.TRUE.equals(tedTalk.getDeleted())) {
            throw new TedTalkException("Ted Talk already deleted!", HttpStatus.BAD_REQUEST);
        } else {
            tedTalk.setDeleted(true);
            this.tedTalkRepository.save(tedTalk);
        }
    }

    @Override
    @Transactional
    public TedTalkDTO updateTedTalk(Long id, TedTalkDTO dto) {
        TedTalkUtils.validateUpdate(dto);
        TedTalk tedTalk = this.tedTalkRepository.findById(id).orElseThrow(() -> new TedTalkException("TED Talk Not Found!", HttpStatus.NOT_FOUND));
        tedTalk.setTitle(dto.getTitle());
        tedTalk.setAuthor(dto.getAuthor());
        tedTalk.setReleaseDate(dto.getDisplayDate() == null ? null : DateUtils.parseToZonedDateTime(dto.getDisplayDate()));
        tedTalk.setDisplayDate(dto.getDisplayDate());
        tedTalk.setViews(dto.getViews());
        tedTalk.setLink(TedTalkUtils.generateLink(dto.getAuthor(), dto.getTitle()));
        return new TedTalkDTO(this.tedTalkRepository.save(tedTalk));
    }
}
