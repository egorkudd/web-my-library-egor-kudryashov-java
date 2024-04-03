package org.example.mapper;

import org.example.dto.BookCardDto;
import org.example.dto.BookRefDto;
import org.example.dto.BookDescDto;
import org.example.entity.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {
    public BookCardDto toBookCardDto(Book book) {
        return new BookCardDto(
                book.getId(),
                book.getImageSource(),
                book.getGenre().getName(),
                book.getMark(),
                book.getName(),
                book.getAuthor().getName(),
                book.getDescription()
        );
    }

    public BookDescDto toSameBookSetDto(Book book) {
        return new BookDescDto(
                book.getId(),
                book.getImageSource(),
                book.getGenre().getName(),
                book.getMark(),
                book.getName(),
                book.getAuthor().getName(),
                book.getDescription(),
                book.getSameBookList().stream().map(this::toBookRefDto).collect(Collectors.toList())
        );
    }

    public BookRefDto toBookRefDto(Book book) {
        return new BookRefDto(
                book.getId(),
                book.getName()
        );
    }
}
