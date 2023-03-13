package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.service.CreatePdfService;
import com.example.gruppebjava.core.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:4200")

public class PersonController {

    private final PersonService personService;
    private final PersonRepo personRepo;
    private final KursRepo kursRepo;


    public PersonController(PersonService personService, PersonRepo personRepo, KursRepo kursRepo) {
        this.personService = personService;
        this.personRepo = personRepo;
        this.kursRepo = kursRepo;
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

    @GetMapping("/pdf/all")
    void pdfPersonListe(HttpServletResponse response) throws IOException {
        System.out.println("Vor Create-Pdf");
        CreatePdfService pdfPersonen = new CreatePdfService(personRepo, kursRepo);
        try{
            pdfPersonen.createPersonenListePdf();
        }catch(IOException e){
            System.out.println("Fehler beim Schreiben der Pdf-Datei!");
        }
        System.out.println("Nach Create-Pdf");
        response.sendRedirect("/download/Personenliste.pdf");
    }

        /*void pdfPersonListe(HttpServletResponse response) throws IOException {
        CreatePdfService pdfPersonen = new CreatePdfService(personRepo, kursRepo);
        pdfPersonen.createPersonenListePdf();
            response.sendRedirect("/resources/download/Personenliste.pdf");
        // List<PersonEntity> persons = personService.findAllPersons();
        // "target/pdf/Personenliste.pdf"
        //return "redirect:target/pdf/Personenliste.pdf";
        //return new ResponseEntity<>(persons, HttpStatus.OK);
    }*/

}
