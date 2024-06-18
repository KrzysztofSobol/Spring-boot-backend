package com.kex.CV.dao;

import com.kex.CV.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String s);
    List<Book> find();

    void update(String id, Book book);

    void delete(String s);
}
