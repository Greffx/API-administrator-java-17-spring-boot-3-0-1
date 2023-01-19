package com.eduardogreff.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FirstPageGreeting {

    @GetMapping
    public ResponseEntity<String> firstPageGreeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok().body(String.format("Hello %s!", name));
    }
}
