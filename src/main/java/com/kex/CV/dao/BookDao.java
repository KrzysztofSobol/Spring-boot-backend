package com.kex.CV.dao;

import com.kex.CV.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String s);
}
