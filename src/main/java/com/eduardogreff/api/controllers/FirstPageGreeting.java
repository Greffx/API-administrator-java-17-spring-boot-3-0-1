package com.eduardogreff.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
@Tag(name = "Greeting", description = "Endpoint to greet people.")
public class FirstPageGreeting {

    @GetMapping
    @Operation(summary = "Greets a person", description = "Says hello to people.",
            responses = {
                    @ApiResponse(description = "Success case", responseCode = "200"),
                    @ApiResponse(description = "Not Found case", responseCode = "404", content = @Content)
            }
    )
    public ResponseEntity<String> firstPageGreeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok().body(String.format("Hello %s!", name));
    }
}
