package com.kex.CV;

import com.kex.CV.domain.Author;
import com.kex.CV.domain.Book;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Tony Stark")
                .age(28)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Megan Fox")
                .age(21)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("John Wick")
                .age(30)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("1111-1111-1111")
                .title("Iron Man")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("2222-2222-2222")
                .title("Iron Man 2")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("3333-3333-3333")
                .title("Iron Man 3")
                .author(author)
                .build();
    }
}
