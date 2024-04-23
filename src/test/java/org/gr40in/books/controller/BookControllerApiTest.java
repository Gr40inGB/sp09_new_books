package org.gr40in.books.controller;

import org.gr40in.books.bean.JUnitSpringBootBase;
import org.gr40in.books.bean.TestConfig;
import org.gr40in.books.dao.Author;
import org.gr40in.books.dao.Book;
import org.gr40in.books.dao.Genre;
import org.gr40in.books.dto.BookDto;
import org.gr40in.books.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.lang3.reflect.TypeUtils;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
//@AutoConfigureWebTestClient
//@ActiveProfiles("test")
//@Testcontainers
class BookControllerApiTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Container
    @ServiceConnection
    public static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @BeforeEach
    void setUp() {

    }


    @Test
    void getBooks() {
        bookRepository.saveAll(List.of(
                Book.builder()
                        .name("Some boring book")
                        .authors(List.of(
                                Author.builder().name("Stiles King").build()
                        ))
                        .genres(List.of(Genre.BORING_THINKS))
                        .build(),
                Book.builder()
                        .name("Some interenting book")
                        .authors(List.of(
                                Author.builder().name("Bill Debill").build()
                        ))
                        .genres(List.of(Genre.SCY_FY))
                        .build()
        ));


        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtoList = webTestClient.get()
                .uri("book")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<BookDto>() {
                })
                .returnResult()
                .getResponseBody();
//
//        List<Book> bookDtoList = restClient.get()
//                .uri("api/book")
//                .retrieve()
//                .body(ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, BookDto.class)));
//
//        Assertions.assertEquals(books.size(), bookDtoList.size());

    }

//    @Test
//    void createBook() {
//    }
//
//    @Test
//    void updateBook() {
//    }
//
//    @Test
//    void getBookById() {
//    }
}