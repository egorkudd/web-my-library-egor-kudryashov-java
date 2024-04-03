package org.example.dto;

import java.util.List;

public record BookDescDto(
        int id,
        String imageSource,
        String genre,
        double mark,
        String name,
        String author,
        String description,
        List<BookRefDto> sameBookList
) {
}
