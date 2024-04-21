package org.gr40in.sp09_new_books.dto;

import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.dao.Genre;
import org.gr40in.sp09_new_books.repository.AuthorsRepository;
import org.gr40in.sp09_new_books.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final String DELIMITER = ", ";

    private final BookRepository bookRepository;
    private final AuthorsRepository authorsRepository;

    public BookDto mapToDto(Book book) {

        BookDto bookDto = new BookDto();

        bookDto.setName(book.getName());
        bookDto.setAuthors(book.getAuthors()
                .stream()
                .map(Author::getName)
                .collect(Collectors.joining(DELIMITER)));
        bookDto.setGenres(book.getGenres()
                .stream()
                .map(Genre::name)
                .collect(Collectors.joining(DELIMITER)));
        return bookDto;
    }

    public Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(book.getName());
        book.setAuthors(authorsRepository
                .findAllByNameIn(Arrays.stream
                        (bookDto.getAuthors().split(DELIMITER)).toList()));
        book.setGenres(Arrays.stream
                (bookDto.getGenres().split(DELIMITER)).map(Genre::valueOf).toList());
        return book;
    }

    public List<Book> mapToListOfBook(List<BookDto> bookDtoList) {
        return bookDtoList.stream().map(this::mapToBook).toList();
    }

    public List<BookDto> mapToListOfBookDto(List<Book> bookList) {
        return bookList.stream().map(this::mapToDto).toList();
    }

}