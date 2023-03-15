package com.example.gruppebjava.core.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.repo.KursRepo;

import com.example.gruppebjava.core.repo.ZuordnungRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class KursServiceTest {

    @Mock
    private KursRepo kursRepo;
    @Mock
    private ZuordnungRepo zuordnungRepo;

    @InjectMocks
    private KursService kursService;

    @Test
    public void addKurs_withValidKurs_returnsSavedKurs() {
        KursEntity kurs = new KursEntity();
        kurs.setStartDatum(getFutureDate());
        when(kursRepo.save(Mockito.any(KursEntity.class))).thenReturn(kurs);

        KursEntity result = kursService.addKurs(kurs);

        verify(kursRepo).save(Mockito.any(KursEntity.class));
        assertEquals(kurs, result);
    }

    @Test
    public void addKurs_withInvalidStartDatum_returnsNull() {
        KursEntity kurs = new KursEntity();
        kurs.setStartDatum(getPastDate());

        KursEntity result = kursService.addKurs(kurs);

        assertNull(result);
    }

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private Date getPastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    @Test
    public void findAllKurse_returnsAllKurse() {
        List<KursEntity> expectedKurse = createMockKurseList();
        when(kursRepo.findAll()).thenReturn(expectedKurse);

        List<KursEntity> actualKurse = kursService.findAllKurse();

        assertEquals(expectedKurse.size(), actualKurse.size());
        assertEquals(expectedKurse.get(0).getId(), actualKurse.get(0).getId());

    }


    @Test
    public void findAllKursesByStatus_returnsMatchingKurses() {
        String status = "ACTIVE";
        List<KursEntity> expectedKurse = createMockKurseList();
        when(kursRepo.findAllByStatus(status)).thenReturn(expectedKurse);

        List<KursEntity> actualKurse = kursService.findAllKursesByStatus(status);

        assertEquals(expectedKurse.size(), actualKurse.size());
        assertEquals(expectedKurse.get(0).getId(), actualKurse.get(0).getId());

    }

    @Test
    public void findKursById_returnsMatchingKurs() {
        Long id = 1L;
        KursEntity expectedKurs = createMockKurs();
        when(kursRepo.findKursEntityById(id)).thenReturn(expectedKurs);

        KursEntity actualKurs = kursService.findKursById(id);

        assertEquals(expectedKurs.getId(), actualKurs.getId());
        assertEquals(expectedKurs.getName(), actualKurs.getName());

    }

    @Test
    public void updateKurs_updatesAndReturnsKurs() {
        KursEntity kurs = createMockKurs();
        kurs.setFreiePlaetze();

        when(kursRepo.save(kurs)).thenReturn(kurs);

        KursEntity updatedKurs = kursService.updateKurs(kurs);

        verify(kursRepo).save(kurs);

        assertEquals(kurs.getId(), updatedKurs.getId());
        assertEquals(kurs.getName(), updatedKurs.getName());
        assertEquals(kurs.getStatus(), updatedKurs.getStatus());
        assertEquals(kurs.getFreiePlaetze(), updatedKurs.getFreiePlaetze());

    }

    private KursEntity createMockKurs() {
        KursEntity kurs = new KursEntity();
        kurs.setId(1L);
        kurs.setStartDatum(getFutureDate());
        kurs.setName("Python");
        kurs.setStatus("ACTIVE");
        kurs.setFreiePlaetze();

        return kurs;
    }


    private List<KursEntity> createMockKurseList() {
        List<KursEntity> kurse = new ArrayList<>();
        KursEntity kurs1 = new KursEntity();
        kurs1.setId(1L);
        kurs1.setName("Python");
        kurs1.setStatus("ACTIVE");

        kurse.add(kurs1);

        KursEntity kurs2 = new KursEntity();
        kurs2.setId(2L);
        kurs2.setName("C++");
        kurs2.setStatus("ACTIVE");

        kurse.add(kurs2);

        KursEntity kurs3 = new KursEntity();
        kurs3.setId(3L);
        kurs3.setName("Java");
        kurs3.setStatus("INACTIVE");

        kurse.add(kurs3);

        return kurse;
    }

    @Test
    public void deleteKurs_deletesKursAndZuordnungen() {
        Long kursId = 1L;

        List<Zuordnung> zuordnungen = createMockZuordnungen(kursId);
        when(zuordnungRepo.findAll()).thenReturn(zuordnungen);

        kursService.deleteKurs(kursId);

        verify(zuordnungRepo).delete(zuordnungen.get(0));
        verify(kursRepo).delete(kursService.findKursById(kursId));
    }

    private List<Zuordnung> createMockZuordnungen(Long kursId) {
        List<Zuordnung> zuordnungen = new ArrayList<>();

        Zuordnung zu1 = new Zuordnung(1L,kursId,true);
        zu1.setKursId(kursId);
        zuordnungen.add(zu1);

        Zuordnung zu2 = new Zuordnung(2L,kursId+1,true);
        zu2.setKursId(kursId + 1);
        zuordnungen.add(zu2);

        return zuordnungen;
    }


}

