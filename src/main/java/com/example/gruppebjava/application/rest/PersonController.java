package com.example.gruppebjava.application.rest;


import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personService.findAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PersonEntity> addEmployee(@RequestBody PersonEntity person){
        PersonEntity newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")

    public ResponseEntity<PersonEntity> getPersonById(@PathVariable("id") Long id) {
        PersonEntity person  = personService.findPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PersonEntity> updatePerson(@RequestBody PersonEntity person){
        PersonEntity updatePerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatePerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable ("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
