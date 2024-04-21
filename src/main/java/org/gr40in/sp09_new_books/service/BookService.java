package org.gr40in.sp09_new_books.service;

import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository repository;

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public Book findBookById(Long id) {
        var book = repository.findById(id);
        if (book.isPresent()) return book.get();
        else throw new NoSuchElementException();
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateBook(Book book) {
        var bookFromDBOptional = repository.findById(book.getId());
        if (bookFromDBOptional.isPresent()) {
            var bookFromDB = bookFromDBOptional.get();
            bookFromDB.setName(book.getName());
            bookFromDB.setAuthors(book.getAuthors());
            bookFromDB.setGenres(book.getGenres());
        } else throw new NoSuchElementException();

    }
}
