package org.gr40in.sp09_new_books.service;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.dao.Genre;
import org.gr40in.sp09_new_books.dto.BookDto;
import org.gr40in.sp09_new_books.dto.BookMapper;
import org.gr40in.sp09_new_books.repository.AuthorsRepository;
import org.gr40in.sp09_new_books.repository.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorsRepository authorsRepository;
    private final BookMapper bookMapper;

    public List<BookDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public BookDto findBookById(Long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) return bookMapper.mapToDto(book.get());
        else throw new NoSuchElementException();
    }

    public BookDto createBook(BookDto book) {
        Book saved = bookRepository.save(bookMapper.mapToBook(book));
        return bookMapper.mapToDto(saved);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(BookDto bookDto) {
        var book = bookMapper.mapToBook(bookDto);
        var bookFromDBOptional = bookRepository.findById(book.getId());
        if (bookFromDBOptional.isPresent()) {
            var bookFromDB = bookFromDBOptional.get();
            bookFromDB.setName(book.getName());
            bookFromDB.setAuthors(book.getAuthors());
            bookFromDB.setGenres(book.getGenres());
        } else throw new NoSuchElementException();
        return bookMapper.mapToDto(book);
    }

    @EventListener(ContextRefreshedEvent.class)
    @Order(2)
    private void generateBooks() {
        if (bookRepository.findAll().isEmpty()) {
            Random random = new Random();
            Faker faker = new Faker();
            for (int i = 0; i < 20; i++)
                authorsRepository.save(Author.builder()
                        .name(faker.book().author())
                        .build());
            List<Author> authors = authorsRepository.findAll();
            for (int i = 0; i < 30; i++) {
                bookRepository.save(Book.builder()
                        .name(faker.book().title())
                        .authors(random.longs(random.nextInt(1,3), 1, 19)
                                .mapToObj(authorsRepository::findById)
                                .map(Optional::get)
                                .toList())
                        .genres(random.ints(2, 1, 4)
                                .mapToObj(j -> Genre.values()[j])
                                .toList())
                        .build());


            }


        }

    }
}
