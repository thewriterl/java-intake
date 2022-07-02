package com.example.javaintake.utils.components;

import com.example.javaintake.domain.dto.TedTalkDTO;
import com.example.javaintake.domain.entity.TedTalk;
import com.example.javaintake.utils.DateUtils;
import com.example.javaintake.utils.exception.TedTalkException;
import org.springframework.http.HttpStatus;

public class TedTalkUtils {

    public static String generateLink(String author, String title) {
        StringBuilder stringBuilder = new StringBuilder("https://ted.com/");
        stringBuilder.append(author.toLowerCase().replace(" ", "_").replace(".", "_"));
        stringBuilder.append(title.toLowerCase().replace(" ", "_"));
        return  stringBuilder.toString();
    }

    public static void validateUpdate(TedTalkDTO dto) {

        if (dto.getDisplayDate() == null) {
            throw new TedTalkException("Date is null!", HttpStatus.BAD_REQUEST);
        }

        if (Boolean.FALSE.equals(DateUtils.isDateValid(dto.getDisplayDate()))) {
            throw new TedTalkException("Date is not Valid!", HttpStatus.BAD_REQUEST);
        }

        if (Boolean.TRUE.equals(dto.getDeleted())) {
            throw new TedTalkException("Ted Talk is Deleted!", HttpStatus.BAD_REQUEST);
        }

        if (dto.getAuthor() == null) {
            throw new TedTalkException("Ted Talk cannot have null author!", HttpStatus.BAD_REQUEST);
        }

        if (dto.getTitle() == null) {
            throw new TedTalkException("Ted Talk cannot have null title!", HttpStatus.BAD_REQUEST);
        }

    }

    public static void validateCreate(TedTalkDTO dto) {
        if (dto.getId() != null) {
            throw new TedTalkException("Ted Talk ID is not null!", HttpStatus.BAD_REQUEST);
        }

        if (Boolean.TRUE.equals(dto.getDeleted())) {
            throw new TedTalkException("Cannot create a deleted Ted Talk!", HttpStatus.BAD_REQUEST);
        }

        if (dto.getTitle() == null) {
            throw new TedTalkException("Ted Talk title is null!", HttpStatus.BAD_REQUEST);
        }

        if (dto.getAuthor() == null) {
            throw new TedTalkException("Ted Talk author is null!", HttpStatus.BAD_REQUEST);
        }

    }
}
