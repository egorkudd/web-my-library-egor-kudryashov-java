package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Genre;
import org.example.repository.GenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genreController")
public class GenreController {
    private final GenreRepository genreRepository;

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getGenreList() {
        return ResponseEntity.ok(genreRepository.findAll());
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> getGenreList(@PathVariable int id) {
        return genreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
