package org.gr40in.sp09_new_books.service;

import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.dto.BookDto;
import org.gr40in.sp09_new_books.dto.BookMapper;
import org.gr40in.sp09_new_books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public List<BookDto> findAllBooks() {
        return repository.findAll()
                .stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public BookDto findBookById(Long id) {
        var book = repository.findById(id);
        if (book.isPresent()) return bookMapper.mapToDto(book.get());
        else throw new NoSuchElementException();
    }

    public void createBook(BookDto book) {
        repository.save(bookMapper.mapToBook(book));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateBook(BookDto bookDto) {
        var book = bookMapper.mapToBook(bookDto);
        var bookFromDBOptional = repository.findById(book.getId());
        if (bookFromDBOptional.isPresent()) {
            var bookFromDB = bookFromDBOptional.get();
            bookFromDB.setName(book.getName());
            bookFromDB.setAuthors(book.getAuthors());
            bookFromDB.setGenres(book.getGenres());
        } else throw new NoSuchElementException();

    }
}
