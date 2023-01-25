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

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    public Page<BookDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(book -> mapper.fromBook(book));
    }

    public BookDTO findById(Long id) {
       return mapper.fromBook(repository.findById(id).orElseThrow(() -> new BookNotFound("This book doesn't exist, try another one.")));
    }

    public void create(BookDTO bookDTO) {
        repository.save(mapper.fromBookDTO(bookDTO));
    }

    public void update(BookDTO bookDTO, Long id) {
        findById(id);
        methodToUpdateABook(repository.getReferenceById(id), bookDTO);
    }

    private void methodToUpdateABook(Book monitoredBook, BookDTO bookDTO) {
        monitoredBook.setTitle(bookDTO.getTitle());
        monitoredBook.setAuthor(bookDTO.getAuthor());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

}
