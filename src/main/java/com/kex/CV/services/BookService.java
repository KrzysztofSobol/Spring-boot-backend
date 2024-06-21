package com.kex.CV.services;

import com.kex.CV.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> find(String isbn);
}
