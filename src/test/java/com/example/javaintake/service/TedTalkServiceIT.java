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

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @Transactional
    void shouldSearchByAuthorTedTalkWithNewlySavedTedTalk() throws Exception {
        var savedTedTalk = this.tedTalkService.createTedTalk(tedTalkDTO);

        var response = this.tedTalkService.search(savedTedTalk.getAuthor(), null, null, null, null);

        assertEquals(1, response.getTotalElements());
        assertEquals(savedTedTalk.getId(), response.getContent().get(0).getId());
    }

    @Test
    @Transactional
    void shouldSearchByTitleTedTalkWithNewlySavedTedTalk() throws Exception {
        var savedTedTalk = this.tedTalkService.createTedTalk(tedTalkDTO);

        var response = this.tedTalkService.search(null, savedTedTalk.getTitle(), null, null, null);

        assertEquals(1, response.getTotalElements());
        assertEquals(savedTedTalk.getId(), response.getContent().get(0).getId());
    }

    @Test
    @Transactional
    void shouldSearchNonExistingTedTalkAndReturnOk() throws Exception {
        var response = this.tedTalkService.search("asdf", null, null, null, null);

        assertEquals(0, response.getTotalElements());
    }

    @Test
    @Transactional
    void shouldDeleteGivenTedTalk() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        this.tedTalkService.delete(response.getId());

        var maybeDeleted = this.tedTalkRepository.findById(response.getId()).orElseThrow();

        assertTrue(maybeDeleted.getDeleted());
    }

    @Test
    @Transactional
    void shouldFailTryingToDeleteNonExistingTedTalk() throws Exception {

        var nullTedTalk = new Random().nextLong();
        assertThrows(TedTalkException.class, () -> this.tedTalkService.delete(nullTedTalk));

    }


    @Test
    @Transactional
    void shouldUpdateGivenTedTalk() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        Faker faker = new Faker();

        response.setTitle(faker.backToTheFuture().character());
        response.setAuthor(faker.gameOfThrones().house());

        this.tedTalkService.updateTedTalk(response.getId(), response);

        var updated = this.tedTalkRepository.findById(response.getId()).orElseThrow();

        assertEquals(updated.getId(), response.getId());
        assertNotEquals(updated.getAuthor(), tedTalkDTO.getAuthor());
        assertNotEquals(updated.getTitle(), tedTalkDTO.getTitle());
    }

    @Test
    @Transactional
    void shouldFailTryingToUpdateWithNullAuthor() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        Faker faker = new Faker();

        response.setTitle(faker.backToTheFuture().character());
        response.setAuthor(null);

        assertThrows(TedTalkException.class, () -> this.tedTalkService.updateTedTalk(response.getId(), response));

    }

    @Test
    @Transactional
    void shouldFailTryingToUpdateWithNullTitle() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        Faker faker = new Faker();

        response.setTitle(null);
        response.setAuthor(faker.gameOfThrones().character());

        assertThrows(TedTalkException.class, () -> this.tedTalkService.updateTedTalk(response.getId(), response));

    }

    @Test
    @Transactional
    void shouldFailTryingToUpdateWithNullDate() throws Exception {
        var response = this.tedTalkService.createTedTalk(tedTalkDTO);

        assertNotNull(response.getId());

        Faker faker = new Faker();

        response.setTitle(faker.backToTheFuture().character());
        response.setAuthor(faker.gameOfThrones().character());
        response.setDisplayDate(null);

        assertThrows(TedTalkException.class, () -> this.tedTalkService.updateTedTalk(response.getId(), response));

    }

}
