package com.eduardogreff.api.controllers;

import com.eduardogreff.domain.entities.dto.BookDTO;
import com.eduardogreff.domain.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoints for manage books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a list of books", description = "Can search all books by URL path", responses = {
            @ApiResponse(description = "Success case", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                    )

            }),
            @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content)
    })
    public ResponseEntity<Page<BookDTO>> findAll(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a book by id", description = "Can search a specific  book by id, passed in path", responses = {
            @ApiResponse(description = "Success case", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)
                    )

            }),
            @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "400", content = @Content)
    })
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Creates a new book", description = "Create a book by passing a reference of object by JSON media type", responses = {
            @ApiResponse(description = "Success case", responseCode = "201", content = {@Content}),
            @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "400", content = @Content)
    })
    public ResponseEntity<Void> create(@RequestBody BookDTO bookDTO) {
        service.create(bookDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.getId()).toUri()).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Updates a existing book, by passing id in path and body with data of JSON media type", responses = {
            @ApiResponse(description = "Success case", responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "400", content = @Content)
    })
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        service.update(bookDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a existing book, by passing id in URL path", responses = {
            @ApiResponse(description = "Success case", responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content),
            @ApiResponse(description = "Not Found case", responseCode = "400", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
