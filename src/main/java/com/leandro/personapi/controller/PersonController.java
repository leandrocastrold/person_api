package com.leandro.personapi.controller;

import com.leandro.personapi.dto.MessageResponseDTO;
import com.leandro.personapi.entity.Person;
import com.leandro.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


// Define a classe como um Controller, e define a rota correspondente
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAll() {
        return "Lista de Pessoas: ";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
