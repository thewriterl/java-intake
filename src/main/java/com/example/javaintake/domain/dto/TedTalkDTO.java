package com.example.javaintake.domain.dto;

import com.example.javaintake.domain.entity.TedTalk;

public class TedTalkDTO {

    private Long id;
    private String title;
    private String author;
    private Long views;
    private Long likes;
    private String link;
    private Boolean deleted;

    private String displayDate;

    public TedTalkDTO() {
        super();
    }

    public TedTalkDTO(TedTalk tedTalk) {
        this.setId(tedTalk.getId());
        this.setTitle(tedTalk.getTitle());
        this.setAuthor(tedTalk.getAuthor());
        this.setViews(tedTalk.getViews());
        this.setLikes(tedTalk.getLikes());
        this.setLink(tedTalk.getLink());
        this.setDisplayDate(tedTalk.getDisplayDate());
        this.setDeleted(tedTalk.getDeleted());
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }
}
