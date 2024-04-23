package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookCardDto;
import org.example.entity.Book;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.service.BookService;
import org.example.service.ContactService;
import org.example.service.GenreService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final GenreService genreService;
    private final ContactService contactService;
    private final BookService bookService;

    @GetMapping("/")
    public String homePage(Model model) {
        collectModel(model);
        model.addAttribute("bookList", bookService.getBookCardList());
        return "index";
    }

    @GetMapping("/book_description")
    public String bookDescriptionPage(@RequestParam int id, Model model) {
        collectModel(model);
        model.addAttribute("book", bookService.getBookDescription(id));
        return "book_description";
    }

    @GetMapping(value = "/book_list", params = "genre")
    public String bookListByGenrePage(@RequestParam int genre, Model model) {
        collectModel(model);
        model.addAttribute("bookList", bookService.getBookListByGenre(genre));
        return "book_list";
    }

    @GetMapping(value = "/book_list", params = "word_request")
    public String bookListByWordLinePage(@RequestParam("word_request") String wordRequest, Model model) {
        collectModel(model);
        model.addAttribute("bookList", bookService.getBookListByWordRequest(wordRequest));
        return "book_list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/add_book_card")
    public String showAddBookCardPage(Model model) {
        collectModel(model);
        BookCardDto bookCardDto = new BookCardDto(0, "", "", 0.0, "", "", "");
        model.addAttribute("bookCardDto", bookCardDto);
        return "add_book_card";
    }

    @PostMapping("/add_book_card")
    public String addBookCard(@ModelAttribute BookCardDto bookCardDto) {
        Book book = bookService.createBook(bookCardDto);
        return "redirect:/";
    }

    private void collectModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAnonymous = authentication instanceof AnonymousAuthenticationToken;
        boolean isAdmin = !isAnonymous && ((User) authentication.getPrincipal()).getRoles().stream().filter(Role::isAdmin).count() == 1;
        model.addAttribute("isAnonymous", isAnonymous);
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("genreList", genreService.getAllGenres());
        model.addAttribute("contactList", contactService.getAllContacts());
    }
}
