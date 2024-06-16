package com.kex.CV;

import com.kex.CV.domain.Author;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Tony Stark")
                .age(28)
                .build();
    }
}
