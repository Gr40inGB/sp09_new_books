package org.gr40in.books.dao;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    private List<Genre> genres;
    @ManyToMany
    private List<Author> authors;
}
