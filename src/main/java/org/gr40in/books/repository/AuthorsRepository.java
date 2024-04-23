package org.gr40in.books.repository;

import org.gr40in.books.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);


    List<Author> findAllByNameIn(List<String> names);

    List<Author> findAllByIdIn(List<Long> idList);
}
