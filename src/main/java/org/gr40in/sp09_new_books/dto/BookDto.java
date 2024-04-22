package org.gr40in.sp09_new_books.dto;

import lombok.Data;

@Data
public class BookDto {
    long id;
    String name;
    String authors;
    String genres;
}
