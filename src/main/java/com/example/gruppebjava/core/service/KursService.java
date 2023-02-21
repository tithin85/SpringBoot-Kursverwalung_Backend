package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.repo.KursRepo;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<KursEntity> findAllKurse(){
        return kursRepo.findAll();
    }

    public KursEntity findKursById(Long id){
        return kursRepo.findKursEntityById(id);
    }

    public KursEntity updateKurs(KursEntity kurs){
        return kursRepo.save(kurs);
    }


    public void deleteKurs(Long id){
        kursRepo.delete(findKursById(id));
    }


}
