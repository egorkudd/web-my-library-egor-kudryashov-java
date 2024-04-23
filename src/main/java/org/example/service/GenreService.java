package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Genre;
import org.example.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) throws Exception {
        return genreRepository.findById(id).orElseThrow(Exception::new);
    }

    public Genre updateGenre(int id, Genre genreDetails) throws Exception {
        Genre genre = genreRepository.findById(id).orElseThrow(Exception::new);
        genre.setName(genreDetails.getName());
        return genreRepository.save(genre);
    }

    // Удаление жанра
    public void deleteGenre(int id) throws Exception {
        Genre genre = genreRepository.findById(id).orElseThrow(Exception::new);
        genreRepository.delete(genre);
    }
}