package com.kex.CV.dao;

import com.kex.CV.domain.Author;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);
    Optional<Author> findOne(long l);
}
