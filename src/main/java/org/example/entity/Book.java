package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_source")
    private String imageSource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private double mark;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER) //TODO : do lazy
    @JoinColumn(name = "author_id")
    private Author author;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_to_same_book",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "same_book_id", referencedColumnName = "id")
    )
    private List<Book> sameBookList;

//    @OneToOne(fetch = FetchType.LAZY)
//    private BookCost bookCost;
}
