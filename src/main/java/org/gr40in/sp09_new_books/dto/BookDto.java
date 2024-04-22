package org.gr40in.sp09_new_books.dto;

import lombok.Data;

@Data
public class BookDto {
    private long id;
    private String name;
    private String authors;
    private String genres;
}
