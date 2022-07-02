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


    /**
     *
     * Gets a paginated filtered list of  {@link TedTalkDTO }
     * @param author the desired author search param
     * @param title the desired title search param
     * @param views the desired views count search param
     * @param likes the desired likes count search param
     * @param page Desired paging.
     * @apiNote if no page is provided, default page is 0
     * @return  List of {@link TedTalkDTO}
     */
    @GetMapping("/search")
    public ResponseEntity<Page<TedTalkDTO>> searchTedTalks(@RequestParam(required = false) String author,
                                                           @RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Long views,
                                                           @RequestParam(required = false) Long likes,
                                                           @RequestParam(required = false) Integer page) {
        return ResponseEntity.ok(this.tedTalkService.search(author, title, views, likes, page));
    }

    /**
     * Deletes a given Ted Talk.
     * @param id the id of the TedTalk to be deleted
     * @return Http Status - No Content
     * @apiNote physical deletion is not safe, this API implements logical deletions.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTedTalk(@PathVariable Long id) {
        this.tedTalkService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing TedTalk
     * @param id of TedTalk to be Updated
     * @param dto the Data Transfer Object containing the info to be updated
     * @return {@link TedTalkDTO}
     * @apiNote Validates if Author, Title and DisplayDate are Null,
     * it won't accept null values in these properties.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TedTalkDTO> updateTedTalk(@PathVariable Long id, @RequestBody TedTalkDTO dto) {
        return ResponseEntity.ok(this.tedTalkService.updateTedTalk(id, dto));
    }

}
