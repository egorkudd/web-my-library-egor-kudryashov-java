//package org.example.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.dto.BookCardDto;
//import org.example.dto.BookDescDto;
//import org.example.mapper.BookMapper;
//import org.example.repository.BookRepository;
//import org.example.repository.GenreRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/bookController")
//public class BookController {
//    private final BookRepository bookRepository;
//    private final GenreRepository genreRepository;
//    private final BookMapper bookMapper;
//
//    @GetMapping("/book_card")
//    public ResponseEntity<List<BookCardDto>> getBookCardList() {
//        return ResponseEntity.ok(
//                bookRepository.findAll().stream().map(bookMapper::toBookCardDto).toList()
//        );
//    }
//
//    @GetMapping("/book_description")
//    public ResponseEntity<BookDescDto> getSameBookList(@RequestParam int id) {
//        return bookRepository.findById(id)
//                .map(book -> ResponseEntity.ok(bookMapper.toSameBookSetDto(book)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/book_card/genre/{genre_id}")
//    public ResponseEntity<List<BookCardDto>> getBookListByGenre(@PathVariable int genre_id) {
//        return genreRepository.findById(genre_id).map(value -> ResponseEntity.ok(
//                bookRepository.findAllByGenre(value).stream().map(bookMapper::toBookCardDto).toList()
//        )).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/book_card/word/{wordRequest}")
//    public ResponseEntity<List<BookCardDto>> getBookListByGenre(@PathVariable String wordRequest) {
//        String request = wordRequest.toLowerCase();
//        return ResponseEntity.ok(bookRepository.findAll().stream()
//                .filter(book -> book.getName().toLowerCase().contains(request))
//                .map(bookMapper::toBookCardDto).toList());
//    }
//}
