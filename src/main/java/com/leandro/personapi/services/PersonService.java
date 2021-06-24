package com.leandro.personapi.services;

import com.leandro.personapi.dto.MessageResponseDTO;
import com.leandro.personapi.dto.PersonDTO;
import com.leandro.personapi.dto.PhoneDTO;
import com.leandro.personapi.entity.Person;
import com.leandro.personapi.entity.Phone;
import com.leandro.personapi.mapper.PersonMapper;
import com.leandro.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {



    @Autowired
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID: " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
    List<Person> people = personRepository.findAll();
    return (List<PersonDTO>) people.stream()
            .map(personMapper::toDTO)
            .collect(Collectors.toList());
    }
}
