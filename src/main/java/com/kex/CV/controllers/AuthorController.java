package com.kex.CV.controllers;

import com.kex.CV.domain.dto.AuthorDto;
import com.kex.CV.domain.entities.AuthorEntity;
import com.kex.CV.mappers.Mapper;
import com.kex.CV.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorService = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorService), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors(){
        List<AuthorEntity> authors = authorService.findAll();
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id){
        Optional<AuthorEntity> author = authorService.find(id);

        return author.map(authorEntity -> new ResponseEntity<>(authorMapper.mapTo(authorEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto){
        if(!authorService.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
    }
}
