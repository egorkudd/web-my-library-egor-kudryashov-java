package org.example.dto;

public record BookCardDto(
    Integer id,
    String imageSource,
    String genre,
    double mark,
    String name,
    String author,
    String description
) {
}
