package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.repo.KursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KursService {
    private final KursRepo kursRepo;

    @Autowired
    public KursService(KursRepo kursRepo) {
        this.kursRepo = kursRepo;
    }

    public KursEntity addKurs(KursEntity kurs){
        kurs.setKursCode(UUID.randomUUID().toString());
        return kursRepo.save(kurs);
    }
}
