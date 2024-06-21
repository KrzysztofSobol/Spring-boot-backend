package com.kex.CV.controllers;

import com.kex.CV.domain.dto.BookDto;
import com.kex.CV.domain.entities.BookEntity;
import com.kex.CV.mappers.Mapper;
import com.kex.CV.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/books")
    public List<BookDto> listBook(){
        List<BookEntity> bookEntites = bookService.findAll();
        return bookEntites.stream().map(bookMapper::mapTo).collect(java.util.stream.Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> book = bookService.find(isbn);

        return book.map(bookEntity -> new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
