package org.gr40in.sp09_new_books.controller;

import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.service.AuthorsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorsService authorsService;



    @GetMapping("create")
    public String createAuthor(Model model) {

        model.addAttribute("author", new Author());
        return "author_create";
    }

    @PostMapping("create")
    public String createAuthor(@ModelAttribute Author author) {
        System.out.println(author);
        authorsService.createAuthor(author);
        System.out.println(author);
        return "redirect:" + author.getId();
    }

    @RequestMapping()
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorsService.findAllAuthors());
        return "authors";
    }

    @RequestMapping("{id}")
    public String getAuthorById(@PathVariable long id, Model model) {
        Author author = authorsService.findAuthorById(id);
        model.addAttribute("author", author);
        return "author";
    }

}
