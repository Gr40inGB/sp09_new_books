package org.gr40in.sp09_new_books.controller;


import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.dao.Book;
import org.gr40in.sp09_new_books.dao.Genre;
import org.gr40in.sp09_new_books.service.AuthorsService;
import org.gr40in.sp09_new_books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorsService authorsService;

    @GetMapping("create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorsService.findAllAuthors());
        model.addAttribute("genres", Genre.values());
        return "book_create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:" + book.getId();
    }

    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:" + book.getId();
    }

    @RequestMapping()
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());

        return "books";
    }

    @RequestMapping("{id}")
    public String getBookById(@PathVariable long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", book.getAuthors());
        model.addAttribute("genres", book.getGenres());
        model.addAttribute("message", "you can edit");
        return "book";
    }

}
