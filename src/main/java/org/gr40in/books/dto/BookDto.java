package org.gr40in.books.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String authors;
    private String genres;
}
