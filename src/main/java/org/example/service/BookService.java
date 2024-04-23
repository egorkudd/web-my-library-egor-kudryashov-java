package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookCardDto;
import org.example.dto.BookDescDto;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.repository.GenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public Book createBook(BookCardDto bookCardDto) {
        return bookRepository.save(bookMapper.toBook(bookCardDto));
    }

    public Book updateBook(int id, BookCardDto bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        book.setImageSource(bookDetails.imageSource());
        book.setGenre(genreRepository.findByName(bookDetails.genre())
                .orElseThrow(() -> new IllegalArgumentException("incorrect genre name"))
        );
        book.setMark(bookDetails.mark());
        book.setName(bookDetails.name());
        book.setAuthor(authorRepository.findByName(bookDetails.author())
                .orElseThrow(() -> new IllegalArgumentException("incorrect author name"))
        );
        book.setDescription(bookDetails.description());

        return bookRepository.save(book);
    }

    public boolean deleteBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) return false;
        bookRepository.delete(book);
        return true;
    }


    public List<BookCardDto> getBookCardList() {
        return bookRepository.findAll().stream().map(bookMapper::toBookCardDto).toList();
    }

    public BookDescDto getBookDescription(int id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDescDto)
                .orElse(null);
    }

    public List<BookCardDto> getBookListByGenre(int genre_id) {
        return genreRepository.findById(genre_id)
                .map(value -> bookRepository.findAllByGenre(value).stream().map(bookMapper::toBookCardDto).toList())
                .orElse(null);
    }

    public List<BookCardDto> getBookListByWordRequest(String wordRequest) {
        String request = wordRequest.strip().toLowerCase().replaceAll("\\s+", " ");
        return bookRepository.findAll().stream()
                .filter(book -> book.getName().toLowerCase().contains(request))
                .map(bookMapper::toBookCardDto).toList();
    }
}
