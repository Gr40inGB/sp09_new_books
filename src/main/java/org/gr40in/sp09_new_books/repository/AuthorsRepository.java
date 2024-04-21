package org.gr40in.sp09_new_books.repository;

import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);


    List<Author> findAllByNameIn(List<String> names);
}
