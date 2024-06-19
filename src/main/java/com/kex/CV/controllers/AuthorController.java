package com.kex.CV.controllers;

import com.kex.CV.domain.dto.AuthorDto;
import com.kex.CV.domain.entities.AuthorEntity;
import com.kex.CV.mappers.Mapper;
import com.kex.CV.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorService = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(savedAuthorService);
    }
}
