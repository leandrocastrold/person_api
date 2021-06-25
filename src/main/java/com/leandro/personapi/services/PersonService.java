package com.leandro.personapi.services;

import com.leandro.personapi.dto.MessageResponseDTO;
import com.leandro.personapi.dto.PersonDTO;
import com.leandro.personapi.entity.Person;
import com.leandro.personapi.exceptions.PersonNotFoundException;
import com.leandro.personapi.mapper.PersonMapper;
import com.leandro.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {


    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        if (personRepository.findPersonByCpf(personDTO.getCpf()).isEmpty()) {
            Person personToSave = personMapper.toModel(personDTO);
            Person savedPerson = personRepository.save(personToSave);
            return getMessageResponseDTO(savedPerson, "created");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF already exists");
    }


    public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
        return (List<PersonDTO>) people.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(int id) throws PersonNotFoundException {
        Person person = verifyIfPersonExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(int id) throws PersonNotFoundException {
        verifyIfPersonExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updatePerson(int id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfPersonExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        personRepository.save(personToUpdate);
        return getMessageResponseDTO(personToUpdate, "updated");
    }

    private Person verifyIfPersonExists(int id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return person;
    }

    private MessageResponseDTO getMessageResponseDTO(Person person, String message) {
        return MessageResponseDTO
                .builder()
                .message("Person with Id " + person.getId() + " " + message)
                .build();
    }

}
