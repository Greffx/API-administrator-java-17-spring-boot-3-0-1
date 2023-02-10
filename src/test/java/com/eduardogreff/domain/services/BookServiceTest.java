package com.eduardogreff.domain.services;

import com.eduardogreff.api.mapper.BookMapper;
import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.dto.BookDTO;
import com.eduardogreff.domain.repositories.BookRepository;
import com.eduardogreff.domain.services.exceptions.BookNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private BookMapper mapper;

    private Book book;
    private BookDTO bookDTO;
    private Optional<Book> optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createBooks();

    }

    @Test
    void findAll() {
        when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(book), PageRequest.of(0, 1), List.of(book).size()));

        Page<Book> result = service.findAll(PageRequest.of(0, 1));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("Harry Potter", result.getContent().get(0).getTitle());
        assertEquals("Jk", result.getContent().get(0).getAuthor());
        assertEquals(Book.class, result.getContent().get(0).getClass());
        assertEquals(new BigDecimal("23.56"), result.getContent().get(0).getPrice());
    }

    @Test
    void whenFindByIdThenShouldReturnABookAndSuccess() {
        when(repository.findById(anyLong())).thenReturn(optional);

        Book result = service.findById(1L);

        assertNotNull(result);
        assertEquals(Book.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals("Jk", result.getAuthor());
        assertEquals(new BigDecimal("23.56"), result.getPrice());
    }

    @Test
    void whenFindByIdThenShouldReturnException() {
        when(repository.findById(anyLong())).thenThrow(new BookNotFound("This book doesn't exist, try another one."));

        try {
            Book result = service.findById(2L);
        } catch (BookNotFound e) {
            assertEquals(BookNotFound.class, e.getClass());
            assertEquals("This book doesn't exist, try another one.", e.getMessage());
        }
    }

    @Test
    void whenCreateThenShouldReturnSuccess() {
        when(repository.save(any())).thenReturn(book);

        Book result = service.create(bookDTO);

        assertNotNull(result);
        assertEquals(Book.class, result.getClass());
        assertEquals(1L, result.getId());
        assertEquals("Jk", result.getAuthor());
        assertEquals(new BigDecimal("23.56"), result.getPrice());
        assertEquals("Harry Potter", result.getTitle());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        when(repository.findById(anyLong())).thenReturn(optional);
        doNothing().when(repository).deleteById(anyLong());

        service.delete(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }

    private void createBooks() {
        book = new Book(1L, "Harry Potter", "Jk", new BigDecimal("23.56"));
        bookDTO = new BookDTO(1L, "Harry Potter", "Jk", new BigDecimal("23.56"));
        optional = Optional.of(book);
    }
}