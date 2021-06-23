package com.leandro.personapi.services;

import com.leandro.personapi.dto.MessageResponseDTO;
import com.leandro.personapi.entity.Person;
import com.leandro.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID: " + savedPerson.getId())
                .build();
    }

}
