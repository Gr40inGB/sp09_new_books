package org.gr40in.books.bean;

import jakarta.persistence.EntityManager;
import org.gr40in.books.dao.Book;
import org.gr40in.books.dto.BookMapper;
import org.gr40in.books.repository.AuthorsRepository;
import org.gr40in.books.repository.BookRepository;
import org.gr40in.books.service.BookService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @MockBean
    private Book auto;

//    @MockBean
//    private EntityManager entityManager;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorsRepository authorsRepository;

    @MockBean
    private BookMapper bookMapper;

    @Bean
    BookService autoService() {
        return BookService.builder()
                .authorsRepository(authorsRepository)
                .bookMapper(bookMapper)
                .bookRepository(bookRepository)
                .build();
    }

}
