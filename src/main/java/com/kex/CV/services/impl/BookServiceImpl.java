package com.kex.CV.services.impl;

import com.kex.CV.domain.entities.BookEntity;
import com.kex.CV.repositories.BookRepository;
import com.kex.CV.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn); // just to be sure
        return bookRepository.save(book);
    }
}
