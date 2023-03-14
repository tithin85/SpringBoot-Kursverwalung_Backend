package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.service.CreatePdfService;
import com.example.gruppebjava.core.service.PersonService;
import com.example.gruppebjava.core.service.ZuordnungService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:4200")

public class PersonController {

    private final PersonService personService;
    private final PersonRepo personRepo;
    private final KursRepo kursRepo;
    private final ZuordnungService zuordnungService;


    public PersonController(PersonService personService, PersonRepo personRepo, KursRepo kursRepo, ZuordnungService zuordnungService) {
        this.personService = personService;
        this.personRepo = personRepo;
        this.kursRepo = kursRepo;
        this.zuordnungService = zuordnungService;
    }

    @GetMapping("/all")
    //@CrossOrigin("http://localhost:4200")
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
    public ResponseEntity<List<PersonEntity>> updatePerson(@RequestBody PersonEntity person){
        PersonEntity updatePerson = personService.updatePerson(person);
        return getAllPersons();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<PersonEntity>> deletePerson(@PathVariable ("id") Long id) {
        personService.deletePerson(id);
        return getAllPersons();
    }

    @GetMapping("/pdf-personenliste")
    void pdfPersonListe(HttpServletResponse response) throws IOException {
        CreatePdfService pdfPersonen = new CreatePdfService(personRepo, kursRepo, zuordnungService);
        try{
            pdfPersonen.createPersonenListePdf();
        }catch(IOException e){
            System.out.println("Fehler beim Schreiben der Personen-Pdf-Datei!");
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Personenliste.pdf\"");
        FileInputStream inputStream = new FileInputStream("src/main/resources/static/download/Personenliste.pdf");
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
