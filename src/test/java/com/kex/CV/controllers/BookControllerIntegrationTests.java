package com.kex.CV.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kex.CV.TestDataUtil;
import com.kex.CV.domain.dto.BookDto;
import com.kex.CV.domain.entities.AuthorEntity;
import com.kex.CV.domain.entities.BookEntity;
import com.kex.CV.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsStatus200ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        BookEntity bookA = TestDataUtil.createTestBookEntityA(null);
        bookService.createBook(bookA.getIsbn(), bookA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value("1111-1111-1111-1111")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("Iron Man")
        );
    }

    @Test
    public void testThatGetBookIdReturnsHttpStatus200ok() throws Exception {
        BookEntity bookA = TestDataUtil.createTestBookEntityA(null);
        bookService.createBook(bookA.getIsbn(), bookA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBookIdReturnsBook() throws Exception {
        BookEntity bookA = TestDataUtil.createTestBookEntityA(null);
        bookService.createBook(bookA.getIsbn(), bookA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1111-1111-1111-1111")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Iron Man")
        );
    }
}
