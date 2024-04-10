package org.example.controller;

import org.example.mapper.BookMapper;
import org.example.repository.BookRepository;
import org.example.repository.ContactRepository;
import org.example.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/")
    public String homePage(Model model, HttpServletRequest request) {
        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("contactList", contactRepository.findAll());
        model.addAttribute(
                "bookCardList",
                bookRepository.findAll().stream().map(bookMapper::toBookCardDto).toList()
        );
        return "index";
    }

    // TODO : Переделать optional.get() (добавить проверку)
    @GetMapping("/book_description")
    public String bookDescriptionPage(@RequestParam int id, Model model, HttpServletRequest request) {
        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("contactList", contactRepository.findAll());
        model.addAttribute(
                "book",
                bookMapper.toBookDescDto(bookRepository.findById(id).get())
        );
        return "book_description";
    }

    // TODO : Переделать optional.get() (добавить проверку)
    @GetMapping(value = "/book_list", params = "genre")
    public String bookListByGenrePage(@RequestParam int genre, Model model, HttpServletRequest request) {
        model.addAttribute("genreList", genreRepository.findAll());
        model.addAttribute("contactList", contactRepository.findAll());
        model.addAttribute(
                "bookList",
                bookRepository.findAllByGenre(genreRepository.findById(genre).get()).stream()
                        .map(bookMapper::toBookCardDto)
                        .toList()
        );
        return "book_list";
    }

    @GetMapping(value = "/book_list", params = "wordLine")
    public String bookListByWordLinePage(@RequestParam String wordLine, Model model, HttpServletRequest request) {
        String finalWordLine = wordLine.strip().toLowerCase().replaceAll("\\s+", " ");
        System.out.println(finalWordLine);
        model.addAttribute(
                "bookList",
                bookRepository.findAll().stream()
                        .filter(book -> book.getName().toLowerCase().contains(finalWordLine))
                        .map(bookMapper::toBookCardDto)
                        .toList()
        );
        return "book_list";
    }
}
