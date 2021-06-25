package com.leandro.personapi.repository;

import com.leandro.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

   public List<Person> findPersonByCpf(String cpf);
}
