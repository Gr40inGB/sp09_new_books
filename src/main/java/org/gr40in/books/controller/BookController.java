package org.gr40in.books.controller;


import lombok.RequiredArgsConstructor;
import org.gr40in.books.dao.Genre;
import org.gr40in.books.dto.BookDto;
import org.gr40in.books.service.AuthorsService;
import org.gr40in.books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorsService authorsService;

    @GetMapping("create")
    public String createBook(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authorsService.findAllAuthors());
        model.addAttribute("genres", Arrays.stream(Genre.values()).toList());
        return "book_create";
    }

    @RequestMapping()
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);

        return "redirect:/book";
    }

    public String updateBook(@ModelAttribute BookDto bookDto) {
        bookService.updateBook(bookDto);
        return "redirect:";
    }

    @RequestMapping("{id}")
    public String getBookById(@PathVariable long id, Model model) {
        BookDto bookDto = bookService.findBookById(id);
        model.addAttribute("book", bookDto);
        model.addAttribute("message", "you can edit");
        return "book";
    }

}
