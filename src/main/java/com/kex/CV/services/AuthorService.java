package com.kex.CV.services;

import com.kex.CV.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> find(Long id);

    boolean exists(Long id);
}
