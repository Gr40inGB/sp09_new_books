package org.gr40in.sp09_new_books.service;

import lombok.RequiredArgsConstructor;
import org.gr40in.sp09_new_books.dao.Author;
import org.gr40in.sp09_new_books.repository.AuthorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AuthorsService {
    private final AuthorsRepository repository;

    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    public Author findAuthorById(Long id) {
        var author = repository.findById(id);
        if (author.isPresent()) return author.get();
        else throw new NoSuchElementException();
    }

    public void createAuthor(Author author) {
        repository.save(author);
    }


    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void updateAuthor(Author author) {
        var authorFromDBOptional = repository.findById(author.getId());
        if (authorFromDBOptional.isPresent()) {
            var authorFromDB = authorFromDBOptional.get();
            authorFromDB.setName(author.getName());
        } else throw new NoSuchElementException();

    }
}
