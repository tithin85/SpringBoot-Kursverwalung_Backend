package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.ZuordnungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Service
public class KursService {
    private final KursRepo kursRepo;
    private final ZuordnungRepo zuordnungrepo;


    @Autowired
    public KursService(KursRepo kursRepo,ZuordnungRepo zuordnungRepo) {
        this.kursRepo = kursRepo;
        this.zuordnungrepo=zuordnungRepo;

    }

    public KursEntity addKurs(KursEntity kurs){
        Date today=new Date();
       if( kurs.getStartDatum().before(today)){
           return null;

       }
        kurs.setMwstEuro();
        kurs.setGebuehrNetto();
        kurs.setEndeDatum();
        kurs.setFreiePlaetze();
        return kursRepo.save(kurs);
    }

    public List<KursEntity> findAllKurse(){
        return kursRepo.findAll();
    }

    public List<KursEntity> findAllKursesByStatus(String status) {
        return kursRepo.findAllByStatus(status);
    }

    public KursEntity findKursById(Long id){
        return kursRepo.findKursEntityById(id);
    }

    public KursEntity updateKurs(KursEntity kurs){
        kurs.setMwstEuro();
        kurs.setGebuehrNetto();
        kurs.setEndeDatum();
        kurs.setFreiePlaetze();

        return kursRepo.save(kurs);
    }


    public void deleteKurs(Long id){
        List<Zuordnung>zuordnungsliste=zuordnungrepo.findAll();
        for(Zuordnung zu:zuordnungsliste){
            if(zu.getKursId()==id)zuordnungrepo.delete(zu);
        }

        kursRepo.delete(findKursById(id));
    }

}
