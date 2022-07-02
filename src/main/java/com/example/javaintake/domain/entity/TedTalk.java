package com.example.javaintake.domain.entity;


import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.utils.DateUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ted_talk")
public class TedTalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "author")
    @NotNull
    private String author;

    @Column(name = "release_date")
    private ZonedDateTime releaseDate;

    @Column(name = "display_date")
    @NotNull
    private String displayDate;

    @Column(name = "total_views")
    private Long views;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "link")
    private String link;

    @Column(name = "deleted")
    private Boolean deleted;

    public TedTalk() {
        super();
    }

    public TedTalk(TedTalkDTO dto) {
        this.setId(dto.getId());
        this.setTitle(dto.getTitle());
        this.setAuthor(dto.getAuthor());
        this.setViews(dto.getViews());
        this.setDisplayDate(dto.getDisplayDate());
        this.setReleaseDate(
                dto.getDisplayDate() == null ? null : DateUtils.parseToZonedDateTime(dto.getDisplayDate()));
        this.setLikes(dto.getLikes());
        this.setDeleted(dto.getDeleted());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

