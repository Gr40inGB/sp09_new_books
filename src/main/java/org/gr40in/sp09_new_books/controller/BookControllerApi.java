package org.gr40in.sp09_new_books.controller;


import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Genre;
import org.gr40in.sp09_new_books.dto.BookDto;
import org.gr40in.sp09_new_books.service.AuthorsService;
import org.gr40in.sp09_new_books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookControllerApi {

    private final BookService bookService;
    private final AuthorsService authorsService;

//    @GetMapping("create")
//    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
//        bookService.createBook(bookDto);
//        return "book_create";
//    }


    @RequestMapping()
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok().body(bookService.findAllBooks());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.createBook(bookDto));
    }

    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.updateBook(bookDto));
    }

    @RequestMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        return ResponseEntity.ok().body(bookService.findBookById(id));
//        model.addAttribute("message", "you can edit");
    }


}
