package com.example.gruppebjava.core.service;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.domain.ZuordnungId;
import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;
import com.example.gruppebjava.core.repo.ZuordnungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZuordnungService {
    private final ZuordnungRepo zuordnungRepo;
    private final PersonRepo personRepo;
    private final KursRepo kursRepo;

    @Autowired
    public ZuordnungService(ZuordnungRepo zuordnungRepo, PersonRepo personRepo, KursRepo kursRepo) {
        this.zuordnungRepo = zuordnungRepo;
        this.personRepo = personRepo;
        this.kursRepo = kursRepo;
    }

    public Zuordnung addPersonAlsTeilnehmer(Zuordnung zuordnung) {

        List<PersonEntity> personlist = personRepo.findAll();
        List<KursEntity> kursList = kursRepo.findAll();
        for (PersonEntity person : personlist) {
            if (person.getId() == zuordnung.getPersonId()) {
                for (KursEntity kurs : kursList) {
                    if (kurs.getId() == zuordnung.getKursId()) return zuordnungRepo.save(zuordnung);
                }
            }
        }


        return null;

    }

    public List<Zuordnung> findZuordnungListe() {
        return zuordnungRepo.findAll();
    }

    public void deleteZuordnung(long personId, long kursId) {
        ZuordnungId id = new ZuordnungId(personId, kursId);
        if (zuordnungRepo.existsById(id)) zuordnungRepo.deleteById(id);
    }

    public List<KursEntity> personalsTeilnehmer(long personId) {
        List<Zuordnung> zu = findZuordnungListe();
        List<KursEntity> resultList = new ArrayList<>();
        for (Zuordnung z : zu) {
            if (z.getPersonId() == personId && z.isTeilnehmer()) {

                resultList.add(kursRepo.findKursEntityById(z.getKursId()));
            }

        }
        return resultList;
    }

    public List<KursEntity> personalsInteressanter(long personId) {
        List<Zuordnung> zu = findZuordnungListe();
        List<KursEntity> resultList = new ArrayList<>();
        for (Zuordnung z : zu) {
            if (z.getPersonId() == personId && !z.isTeilnehmer()) {
                resultList.add(kursRepo.findKursEntityById(z.getKursId()));
            }

        }
        return resultList;
    }

    public List<PersonEntity> teilnehmerListe(long kursId) {
        List<Zuordnung> zu = findZuordnungListe();
        List<PersonEntity> resultList = new ArrayList<>();
        for (Zuordnung z : zu) {
            if (z.getKursId() == kursId && z.isTeilnehmer()) {
                resultList.add(personRepo.findPersonEntityById(z.getPersonId()));
            }

        }
        return resultList;


    }

    public List<PersonEntity> interessanterListe(long kursId) {
        List<Zuordnung> zu = findZuordnungListe();
        List<PersonEntity> resultList = new ArrayList<>();
        for (Zuordnung z : zu) {
            if (z.getKursId() == kursId && !z.isTeilnehmer()) {
                resultList.add(personRepo.findPersonEntityById(z.getPersonId()));
            }

        }
        return resultList;


    }

    public List<PersonEntity> remainingPersonen(long kursId) {
        List<PersonEntity> resultList = personRepo.findAll();
        for (PersonEntity person : teilnehmerListe(kursId)) {
            resultList.remove(person);
        }
        for (PersonEntity person : interessanterListe(kursId)) {
            resultList.remove(person);

        }
        return resultList;

    }

    public List<KursEntity> remainingKurses(long personId) {
        List<KursEntity> resultList = kursRepo.findAll();
        for (KursEntity kurs : personalsTeilnehmer(personId)) {
            resultList.remove(kurs);
        }
        for (KursEntity kurs : personalsInteressanter(personId)) {
            resultList.remove(kurs);

        }
        return resultList;

    }
}