package com.kex.CV.mappers.impl;

import com.kex.CV.domain.dto.BookDto;
import com.kex.CV.domain.entities.BookEntity;
import com.kex.CV.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {
    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
