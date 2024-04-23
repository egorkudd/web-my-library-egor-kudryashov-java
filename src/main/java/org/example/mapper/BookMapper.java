package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookCardDto;
import org.example.dto.BookRefDto;
import org.example.dto.BookDescDto;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

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

    public BookDescDto toBookDescDto(Book book) {
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

    public Book toBook(BookCardDto bookCardDto) {
        Book book = new Book();
        book.setImageSource(bookCardDto.imageSource());
        book.setGenre(genreRepository.findByName(bookCardDto.genre())
                .orElseThrow(() -> new IllegalArgumentException("incorrect genre name"))
        );
        book.setMark(bookCardDto.mark());
        book.setName(bookCardDto.name());
        book.setAuthor(authorRepository.findByName(bookCardDto.author())
                .orElseThrow(() -> new IllegalArgumentException("incorrect author name"))
        );
        book.setDescription(bookCardDto.description());
        book.setSameBookList(new ArrayList<>());
        return book;
    }
}
