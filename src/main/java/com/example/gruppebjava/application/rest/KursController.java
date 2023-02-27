package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.service.KursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kurs")
@CrossOrigin("http://localhost:4200")

public class KursController {
    private final KursService kursService;

    public KursController(KursService kursService) {
        this.kursService = kursService;
    }


   @PostMapping(value = "/add")
    public ResponseEntity<KursEntity> addKurs(@RequestBody KursEntity kurs){
        KursEntity newKurs =kursService.addKurs(kurs);
        return  new ResponseEntity<>(newKurs, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<KursEntity>> getAllKurse(){
       List<KursEntity> kursEntityList=kursService.findAllKurse();
        return  new ResponseEntity<>(kursEntityList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<KursEntity> getKursById(@PathVariable("id") Long id){
        KursEntity kurs=kursService.findKursById(id);
        return  new ResponseEntity<>(kurs, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<KursEntity> updateKurs(@RequestBody KursEntity kurs){
        KursEntity updatetKurs = kursService.updateKurs(kurs);
        return new ResponseEntity<>(updatetKurs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<KursEntity> deleteKurs(@PathVariable("id") Long id){
        //<?> return anything
        kursService.deleteKurs(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }







}
