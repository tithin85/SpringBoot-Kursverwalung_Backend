package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.service.KursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kurs")
public class KursResource {
    private final KursService kursService;

    public KursResource(KursService kursService) {
        this.kursService = kursService;
    }
    @PostMapping("/add")
    public ResponseEntity<KursEntity> addEmployee(@RequestBody KursEntity kurs){
        KursEntity newKurs =kursService.addKurs(kurs);
        return  new ResponseEntity<>(newKurs, HttpStatus.CREATED);
    }
}
