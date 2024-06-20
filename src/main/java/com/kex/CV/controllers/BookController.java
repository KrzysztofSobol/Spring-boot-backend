package com.kex.CV.controllers;

import com.kex.CV.domain.dto.BookDto;
import com.kex.CV.domain.entities.BookEntity;
import com.kex.CV.mappers.Mapper;
import com.kex.CV.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
}
