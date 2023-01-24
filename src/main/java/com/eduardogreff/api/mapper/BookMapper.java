package com.eduardogreff.api.mapper;

import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMapper {

    private final ModelMapper mapper = new ModelMapper();

    public BookDTO fromBook(Book book) {
        return mapper.map(book, BookDTO.class);
    }

    public Book fromBookDTO(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

    public List<BookDTO> fromListOfBooks(List<Book> books) {
        return books.stream().map(this::fromBook).toList();
    }
}
