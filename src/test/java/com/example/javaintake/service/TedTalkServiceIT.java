package com.example.javaintake.service;

import com.example.javaintake.JavaIntakeApplication;
import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.repository.TedTalkRepository;
import com.example.javaintake.utils.exception.TedTalkException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = JavaIntakeApplication.class)
@Transactional
public class TedTalkServiceIT {

    @Autowired
    private TedTalkRepository tedTalkRepository;

    @Autowired
    private TedTalkService tedTalkService;

    private TedTalkDTO tedTalkDTO;

    @BeforeEach
    public void init() {
        Faker faker = new Faker();
        tedTalkDTO = new TedTalkDTO();
        tedTalkDTO.setAuthor(faker.backToTheFuture().character());
        tedTalkDTO.setTitle(faker.gameOfThrones().dragon());
        tedTalkDTO.setLikes(300L);
        tedTalkDTO.setViews(300L);
        tedTalkDTO.setDisplayDate("July 2010");
    }

    @Test
    @Transactional
    void shouldCreateGivenTedTalk() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        var savedTedTalk = this.tedTalkRepository.findById(response.getId()).orElseThrow();

        assertNotNull(savedTedTalk.getReleaseDate());
        assertNotNull(savedTedTalk.getLink());
        assertNotNull(savedTedTalk.getDisplayDate());
        assertNotNull(response.getLink());

    }

    @Test
    @Transactional
    void shouldFailCreatingTedTalkWithID() throws Exception {
        tedTalkDTO.setId(12394L);
        assertThrows(TedTalkException.class, () -> this.tedTalkService.createTedTalk(tedTalkDTO));
    }

    @Test
    @Transactional
    void shouldFailCreatingTedTalkWithDeletedTrue() throws Exception {
        tedTalkDTO.setDeleted(true);
        assertThrows(TedTalkException.class, () -> this.tedTalkService.createTedTalk(tedTalkDTO));
    }

    @Test
    @Transactional
    void shouldFailCreatingTedTalkWithNullTitle() throws Exception {
        tedTalkDTO.setTitle(null);
        assertThrows(TedTalkException.class, () -> this.tedTalkService.createTedTalk(tedTalkDTO));
    }

    @Test
    @Transactional
    void shouldFailCreatingTedTalkWithNullAuthor() throws Exception {
        tedTalkDTO.setAuthor(null);
        assertThrows(TedTalkException.class, () -> this.tedTalkService.createTedTalk(tedTalkDTO));
    }

    @Test
    @Transactional(readOnly = true)
    void shouldFindTedTalksWithPaging() throws Exception {
        var response = this.tedTalkService.getAll(0);
        assertNotNull(response);
        assertEquals(50, response.getContent().size());
    }

    @Test
    @Transactional(readOnly = true)
    void shouldFindTedTalksWithPagingNull() throws Exception {
        var response = this.tedTalkService.getAll(null);
        assertNotNull(response);
        assertEquals(50, response.getContent().size());
    }


}
