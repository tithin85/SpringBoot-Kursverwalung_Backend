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
@DeleteMapping("/delete/{personId}/{kursId}")
    public ResponseEntity<?>deleteById(@PathVariable("personId")long personId,@PathVariable("kursId")long kursId){
        zuordnungService.deleteZuordnung(personId, kursId);
        return new ResponseEntity<>(HttpStatus.OK);
}
}
