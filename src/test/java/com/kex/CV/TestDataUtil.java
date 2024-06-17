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

    public static Book createTestBook() {
        return Book.builder()
                .isbn("3124-4131-3451")
                .title("Iron Man")
                .authorId(1L)
                .build();
    }
}
