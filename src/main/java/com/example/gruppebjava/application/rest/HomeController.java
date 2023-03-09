package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.service.KursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:4200")

public class HomeController {

    private final KursService kursService;

    public HomeController(KursService kursService) {
        this.kursService = kursService;
    }

    @GetMapping("/homekurslist")
    public ResponseEntity<List<KursEntity>> getAllKurse(){
        List<KursEntity> kursEntityList = kursService.findAllKursesByStatus("geplant");
        kursEntityList.addAll(kursService.findAllKursesByStatus("aktiv"));
        return  new ResponseEntity<>(kursEntityList, HttpStatus.OK);
    }

    @GetMapping("/homekursdetails/{id}")
    public ResponseEntity<KursEntity> getKursById(@PathVariable("id") Long id){
        KursEntity kurs=kursService.findKursById(id);
        return  new ResponseEntity<>(kurs, HttpStatus.OK);
    }
}


