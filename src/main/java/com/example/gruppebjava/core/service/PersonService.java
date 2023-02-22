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

        String pattern = ("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(person.getEmail());
        if( !m.matches()) return null;
        return personRepo.save(person);

    }

    public List<PersonEntity> findAllPersons (){
        return personRepo.findAll();
    }

    public PersonEntity findPersonById(Long id){
    return personRepo.findPersonEntityById(id);
    }

    public void deletePerson (Long id){
        /*List<PersonEntity>persons=personRepo.findAll();
        for(PersonEntity person:persons){
            if(person.getId()==id)personRepo.delete(person);
        }*/
        personRepo.deleteById(id);

        personRepo.deleteById(id);


    }

    public PersonEntity updatePerson(PersonEntity person) {
        return personRepo.save(person);
    }



}
