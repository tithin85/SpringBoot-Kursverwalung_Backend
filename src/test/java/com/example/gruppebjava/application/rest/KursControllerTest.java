package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;
import com.example.gruppebjava.core.service.KursService;
import com.example.gruppebjava.core.service.PersonService;
import com.example.gruppebjava.core.service.ZuordnungService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class KursControllerTest {

    @Mock
    private KursService kursService;

    @Mock
    private PersonRepo personRepo;

    @Mock
    private KursRepo kursRepo;

    @Mock
    private ZuordnungService zuordnungService;

    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private KursController kursController;

        @Test
        public void addKurs_returnsCreatedHttpStatus() {
            KursEntity kurs = createMockKurs();
            when(kursService.addKurs(kurs)).thenReturn(kurs);

            ResponseEntity<KursEntity> response = kursController.addKurs(kurs);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        private KursEntity createMockKurs() {
            KursEntity kurs = new KursEntity();
            kurs.setId(1L);
            kurs.setName("Python");
            kurs.setStartDatum(getFutureDate());
            return kurs;
        }

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }


    @Test
    public void getAllKurse_returnsListOfKurse() {
        List<KursEntity> kursList = createMockKurseList();
        when(kursService.findAllKurse()).thenReturn(kursList);

        ResponseEntity<List<KursEntity>> response = kursController.getAllKurse();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(kursList, response.getBody());
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

}