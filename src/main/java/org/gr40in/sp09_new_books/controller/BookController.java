package org.gr40in.sp09_new_books.controller;


import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.dao.Genre;
import org.gr40in.sp09_new_books.dto.BookDto;
import org.gr40in.sp09_new_books.service.AuthorsService;
import org.gr40in.sp09_new_books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

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

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:";
    }

    public String updateBook(@ModelAttribute BookDto bookDto) {
        bookService.updateBook(bookDto);
        return "redirect:";
    }

    @RequestMapping()
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @RequestMapping("{id}")
    public String getBookById(@PathVariable long id, Model model) {
        BookDto bookDto = bookService.findBookById(id);
        model.addAttribute("book", bookDto);
        model.addAttribute("authors", bookDto.getAuthors());
        model.addAttribute("genres", bookDto.getGenres());
        model.addAttribute("message", "you can edit");
        return "book";
    }

}
