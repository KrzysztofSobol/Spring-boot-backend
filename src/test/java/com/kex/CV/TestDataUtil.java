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

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1111-1111-1111")
                .title("Iron Man")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("2222-2222-2222")
                .title("Iron Man 2")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("3333-3333-3333")
                .title("Iron Man 3")
                .authorId(1L)
                .build();
    }
}
