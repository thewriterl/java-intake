package com.example.javaintake.controller;


import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.domain.entity.TedTalk;
import com.example.javaintake.service.TedTalkService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ted-talks")
public class TedTalkController {

    private final TedTalkService tedTalkService;


    public TedTalkController(TedTalkService tedTalkService) {
        this.tedTalkService = tedTalkService;
    }

    /**
     * <p>
     * Creates a new {@link TedTalk } provided in the request body.
     * Will validate if the ID is null and must not provide a <b>deleted = false</b> prop.
     * </p>
     * @param dto - The Data Transfer Object for a Ted Talk.
     * @return {@link TedTalkDTO}
     */
    @PostMapping
    public ResponseEntity<TedTalkDTO> createTedTalk(@RequestBody TedTalkDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.tedTalkService.createTedTalk(dto));
    }

    /**
     * <p>
     * Gets a paginated unfiltered list of {@link TedTalkDTO }.
     * </p>
     * @param page - Desired paging.
     * @return List of {@link TedTalkDTO}
     * @apiNote if no page is provided, default page is 0
     */
    @GetMapping
    public ResponseEntity<Page<TedTalkDTO>> getAllTedTalks(@RequestParam(required = false) Integer page) {
        return ResponseEntity.ok(this.tedTalkService.getAll(page));
    }


}
