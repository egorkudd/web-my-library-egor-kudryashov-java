package org.example.dto;

public record BookCardDto(
    int id,
    String imageSource,
    String genre,
    double mark,
    String name,
    String author,
    String description
) {
}
