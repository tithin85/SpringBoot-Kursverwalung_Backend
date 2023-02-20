package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    @Autowired

    public PersonService(PersonRepo personRepo){
        this.personRepo= personRepo;

    }

    public PersonEntity addPerson(PersonEntity person){
        return personRepo.save(person);

    }

    public List<PersonEntity> findAllPersons (){
        return personRepo.findAll();
    }

    public PersonEntity findPersonById(Long id){
    return personRepo.findPersonEntityById(id);
    }

    public void deletePerson (Long id){

        personRepo.deletePersonEntityById(id);

    }

    public PersonEntity updatePerson(PersonEntity person) {
        return personRepo.save(person);
    }
}
