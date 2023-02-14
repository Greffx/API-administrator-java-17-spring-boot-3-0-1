package com.eduardogreff.api.controllers;

import com.eduardogreff.api.mapper.BookMapper;
import com.eduardogreff.domain.entities.Book;
import com.eduardogreff.domain.entities.dto.BookDTO;
import com.eduardogreff.domain.repositories.BookRepository;
import com.eduardogreff.domain.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private BookMapper mapper;

    private Book book;

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createBooks();
    }

    @Test
    void findAll() {
        when(service.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(List.of(book), PageRequest.of(0, 1), List.of(book).size()));

        ResponseEntity<Page<BookDTO>> result = controller.findAll(PageRequest.of(0, 2));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getTotalPages());
        assertEquals(1, result.getBody().getTotalElements());

    }

    @Test
    void findById() {
        when(service.findById(anyLong())).thenReturn(book);
        when(mapper.fromBook(book)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = controller.findById(1L);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2L, result.getBody().getId());
        assertEquals("Jk", result.getBody().getAuthor());
        assertEquals(new BigDecimal("23.56"), result.getBody().getPrice());
        assertEquals("Harry Potter", result.getBody().getTitle());
        assertEquals(BookDTO.class, result.getBody().getClass());

    }

    @Test
    void create() {
        when(service.create(any())).thenReturn(book);
        when(mapper.fromBook(any())).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = controller.create(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getHeaders().get("Location"));
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(BookDTO.class, result.getBody().getClass());
        assertEquals(2L, result.getBody().getId());
        assertEquals("Jk", result.getBody().getAuthor());
        assertEquals("Harry Potter", result.getBody().getTitle());
        assertEquals(new BigDecimal("23.56"), result.getBody().getPrice());

    }

    @Test
    void update() {
        when(service.update(any(), anyLong())).thenReturn(book);
        when(mapper.fromBook(any())).thenReturn(bookDTO);

        ResponseEntity<BookDTO> result = controller.update(1L, bookDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2L, result.getBody().getId());
        assertEquals("Jk", result.getBody().getAuthor());
        assertEquals("Harry Potter", result.getBody().getTitle());
        assertEquals(new BigDecimal("23.56"), result.getBody().getPrice());
    }

    @Test
    void delete() {
        doNothing().when(service).delete(anyLong());

        controller.delete(1L);

        verify(service, times(1)).delete(1L);
    }

    private void createBooks() {
        book = new Book(1L, "Harry Potter", "Jk", new BigDecimal("23.56"));
        bookDTO = new BookDTO(2L, "Harry Potter", "Jk", new BigDecimal("23.56"));
    }
}