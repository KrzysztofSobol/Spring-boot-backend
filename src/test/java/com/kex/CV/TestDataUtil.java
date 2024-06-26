package com.kex.CV;

import com.kex.CV.domain.dto.AuthorDto;
import com.kex.CV.domain.dto.BookDto;
import com.kex.CV.domain.entities.AuthorEntity;
import com.kex.CV.domain.entities.BookEntity;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Tony Stark")
                .age(28)
                .build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L)
                .name("Tony Stark")
                .age(28)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Megan Fox")
                .age(21)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("John Wick")
                .age(30)
                .build();
    }

    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1111-1111-1111-1111")
                .title("Iron Man")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("1111-1111-1111-1111")
                .title("Iron Man")
                .author(authorDto)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("2222-2222-2222")
                .title("Iron Man 2")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("3333-3333-3333")
                .title("Iron Man 3")
                .authorEntity(authorEntity)
                .build();
    }
}
