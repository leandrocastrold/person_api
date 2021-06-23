package com.leandro.personapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Define a classe como um Controller, e define a rota correspondente
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @GetMapping
    public String getPeople() {
        return "Hello People";
    }
}
