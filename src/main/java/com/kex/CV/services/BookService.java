package com.kex.CV.services;

import com.kex.CV.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> find(String isbn);

    boolean exists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);
}
