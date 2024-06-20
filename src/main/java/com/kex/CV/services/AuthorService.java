package com.kex.CV.services;

import com.kex.CV.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
