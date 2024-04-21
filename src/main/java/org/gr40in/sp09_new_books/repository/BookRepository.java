package org.gr40in.sp09_new_books.repository;

import org.gr40in.sp09_new_books.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
