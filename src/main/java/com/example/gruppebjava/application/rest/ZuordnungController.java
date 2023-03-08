package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.service.ZuordnungService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zuordnung")
@CrossOrigin("http://localhost:4200")
public class ZuordnungController {
    private final ZuordnungService zuordnungService;

    public ZuordnungController(ZuordnungService zuordnungService) {
        this.zuordnungService = zuordnungService;
    }

    @GetMapping("/all")

    public ResponseEntity<List<Zuordnung>> getAllZuordnungListe() {
        List<Zuordnung> zuordnungList = zuordnungService.findZuordnungListe();
        return new ResponseEntity<>(zuordnungList, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Zuordnung> addAllZuordnungListe(@RequestBody Zuordnung zuordnung) {
        Zuordnung zu = zuordnungService.addPersonAlsTeilnehmer(zuordnung);
        return new ResponseEntity<>(zu, HttpStatus.OK);
    }
    @PutMapping("/updateTeilnahme")
    public ResponseEntity<Zuordnung>updateTeilnahme(@RequestBody Zuordnung zuordnung) {
        Zuordnung zuordnung1=zuordnungService.updateTeilnahmeStatus(zuordnung);
        return new ResponseEntity<>(zuordnung1, HttpStatus.OK);
    }




    @DeleteMapping("/delete/{personId}/{kursId}")
    public ResponseEntity<?> deleteById(@PathVariable("personId") long personId, @PathVariable("kursId") long kursId) {
        zuordnungService.deleteZuordnung(personId, kursId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/personAlsTeilnehmer/{personId}")
    public ResponseEntity<List<KursEntity>> getTeilnehmeKurse(@PathVariable("personId") long personId) {
        List<KursEntity> result = zuordnungService.personalsTeilnehmer(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/personAlsInteressanter/{personId}")
    public ResponseEntity<List<KursEntity>> getInteressierteKurse(@PathVariable("personId") long personId) {
        List<KursEntity> result = zuordnungService.personalsInteressanter(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/kursTeilnehmer/{kursId}")
    public ResponseEntity<List<PersonEntity>> getKursTeilnehmer(@PathVariable("kursId") long kursId) {
        List<PersonEntity> result = zuordnungService.teilnehmerListe(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/kursInteressanter/{kursId}")
    public ResponseEntity<List<PersonEntity>> getKursInteressanter(@PathVariable("kursId") long kursId) {
        List<PersonEntity> result = zuordnungService.interessanterListe(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/remainingKurs/{personId}")
    public ResponseEntity<List<KursEntity>>getRemainingKurs(@PathVariable("personId") long personId){
        List<KursEntity> result = zuordnungService.remainingKurses(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping("/remainingPersonen/{kursId}")
    public ResponseEntity<List<PersonEntity>>getRemainingPersonen(@PathVariable("kursId") long kursId){
        List<PersonEntity> result = zuordnungService.remainingPersonen(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
