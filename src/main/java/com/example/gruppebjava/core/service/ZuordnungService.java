package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.repo.ZuordnungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZuordnungService {
    private final ZuordnungRepo zuordnungRepo;
@Autowired
    public ZuordnungService(ZuordnungRepo zuordnungRepo) {
        this.zuordnungRepo = zuordnungRepo;
    }

    public Zuordnung addPersonAlsTeilnehmer(PersonEntity person, KursEntity kurs){
    return zuordnungRepo.save(new Zuordnung());

    }

    public List<Zuordnung> findZuordnungListe() {
    return zuordnungRepo.findAll();
    }
}
