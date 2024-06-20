package com.kex.CV.services;

import com.kex.CV.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);
}
