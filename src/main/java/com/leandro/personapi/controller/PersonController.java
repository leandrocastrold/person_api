package com.leandro.personapi.controller;

import com.leandro.personapi.dto.MessageResponseDTO;
import com.leandro.personapi.dto.PersonDTO;
import com.leandro.personapi.dto.PhoneDTO;
import com.leandro.personapi.entity.Person;
import com.leandro.personapi.exceptions.PersonNotFoundException;
import com.leandro.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable int id) throws PersonNotFoundException {

        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) throws Exception {
        return personService.createPerson(personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable int id) throws PersonNotFoundException {
        personService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO updadePerson(@PathVariable int id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
       return personService.updatePerson(id, personDTO);
    }



}
