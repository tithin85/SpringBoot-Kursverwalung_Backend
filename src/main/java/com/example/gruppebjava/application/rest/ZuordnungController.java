package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.service.KursService;
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
    private final KursService kursService;

    public ZuordnungController(ZuordnungService zuordnungService,KursService kursService) {
        this.zuordnungService = zuordnungService;
        this.kursService=kursService;
    }

    @GetMapping("/all")

    public ResponseEntity<List<Zuordnung>> getAllZuordnungListe() {
        List<Zuordnung> zuordnungList = zuordnungService.findZuordnungListe();
        return new ResponseEntity<>(zuordnungList, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Zuordnung> addAllZuordnungListe(@RequestBody Zuordnung zuordnung) {
        Zuordnung zu = zuordnungService.addPersonAlsTeilnehmer(zuordnung);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(zu.getKursId());
        KursEntity kurs=kursService.findKursById(zu.getKursId());
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);

        return new ResponseEntity<>(zu, HttpStatus.OK);
    }
    @PutMapping("/updateTeilnahme")
    public ResponseEntity<Zuordnung>updateTeilnahme(@RequestBody Zuordnung zuordnung) {
        Zuordnung zu=zuordnungService.updateTeilnahmeStatus(zuordnung);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(zu.getKursId());
        KursEntity kurs=kursService.findKursById(zu.getKursId());
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);
        return new ResponseEntity<>(zu, HttpStatus.OK);
    }




    @DeleteMapping("/delete/{personId}/{kursId}")
    public ResponseEntity<?> deleteById(@PathVariable("personId") long personId, @PathVariable("kursId") long kursId) {
        zuordnungService.deleteZuordnung(personId, kursId);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(kursId);
        KursEntity kurs=kursService.findKursById(kursId);
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);
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
