package com.eduardogreff.domain.services;

import com.eduardogreff.api.mapper.BookMapper;
import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.dto.BookDTO;
import com.eduardogreff.domain.repositories.BookRepository;
import com.eduardogreff.domain.services.exceptions.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Book findById(Long id) {
       return repository.findById(id).orElseThrow(() -> new BookNotFound("This book doesn't exist, try another one."));
    }

    public Book create(BookDTO bookDTO) {
        return repository.save(mapper.fromBookDTO(bookDTO));
    }

    public Book update(BookDTO bookDTO, Long id) {
        bookDTO.setId(id);
        return repository.save(mapper.fromBookDTO(bookDTO));
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
