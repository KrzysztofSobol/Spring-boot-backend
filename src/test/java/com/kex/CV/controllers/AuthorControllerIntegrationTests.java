package com.kex.CV.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kex.CV.TestDataUtil;
import com.kex.CV.domain.dto.AuthorDto;
import com.kex.CV.domain.entities.AuthorEntity;
import com.kex.CV.services.AuthorService;
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
public class AuthorControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Crated() throws Exception {
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        authorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        authorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Tony Stark")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("28")
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception{
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Tony Stark")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(28)
        );
    }

    @Test
    public void testThatGetAuthorsIdReturnsHttpStatus200() throws Exception {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        authorService.save(author);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorsIdReturnsAnAuthor() throws Exception {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        authorService.save(author);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Tony Stark")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(28)
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsHttpStatus404WhenNoFound() throws Exception {
        AuthorDto author = TestDataUtil.createTestAuthorDtoA();
        String authorJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsHttpStatus200WhenFound() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);

        AuthorDto author = TestDataUtil.createTestAuthorDtoA();
        String authorJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + author.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateAuthorReturnsAnUpdatedAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);

        AuthorDto author = TestDataUtil.createTestAuthorDtoA();
        author.setName("Rick");
        author.setAge(200);
        String authorJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + author.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(author.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(author.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateReturnsAnUpdatedAuthor() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorService.save(authorEntity);

        String authorJson = "{\"name\":\"Slim Shady\"}";
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(authorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Slim Shady")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(28)
        );
    }
}
