package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookCardDto;
import org.example.dto.BookDescDto;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book_controller")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createBook(@RequestBody BookCardDto book) {
        try {
            Book createdBook = bookService.createBook(book);
            return new ResponseEntity<>(createdBook.getId(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateBook(@PathVariable Integer id, @RequestBody BookCardDto bookDetails) {
        try {
            Book book = bookService.updateBook(id, bookDetails);
            return new ResponseEntity<>(book.getId(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Integer id) {
        boolean bookDeleted = bookService.deleteBook(id);
        if (bookDeleted) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/book_card")
    public ResponseEntity<List<BookCardDto>> getBookCardList() {
        return ResponseEntity.ok(bookService.getBookCardList());
    }

    @GetMapping("/book_description")
    public ResponseEntity<BookDescDto> getBookDescription(@RequestParam int id) {
        BookDescDto bookDescDto = bookService.getBookDescription(id);
        return bookDescDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(bookDescDto);
    }

    @GetMapping("/book_card/genre/{genre_id}")
    public ResponseEntity<List<BookCardDto>> getBookListByGenre(@PathVariable("genre_id") int genreId) {
        List<BookCardDto> bookCardDtoList = bookService.getBookListByGenre(genreId);
        return bookCardDtoList == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(bookCardDtoList);
    }

    @GetMapping("/book_card/word/{word_request}")
    public ResponseEntity<List<BookCardDto>> getBookListByWordRequest(@PathVariable("word_request") String wordRequest) {
        return ResponseEntity.ok(bookService.getBookListByWordRequest(wordRequest));
    }
}
