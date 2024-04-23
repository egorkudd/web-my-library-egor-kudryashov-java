package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) throws Exception {
        return authorRepository.findById(id).orElseThrow(Exception::new);
    }

    public Author updateAuthor(int id, Author authorDetails) throws Exception {
        Author author = authorRepository.findById(id).orElseThrow(Exception::new);
        author.setName(authorDetails.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(int id) throws Exception {
        Author author = authorRepository.findById(id).orElseThrow(Exception::new);
        authorRepository.delete(author);
    }
}