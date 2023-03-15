package com.example.gruppebjava.core.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KursEntityTest {
    KursEntity kurs = new KursEntity();

    @Test
    public void testSetEndeDatum() {

        Date today = new Date();
        kurs.setStartDatum(today);
        kurs.setAnzahlTage(14);
        kurs.setWieOftinWoche(2);
        kurs.setEndeDatum();
        long expectedEnd = today.getTime() + (14 * 7 * 86400000L / 2);
        Date expectedEndDate = new Date(expectedEnd);
        assertEquals(expectedEndDate, kurs.getEndeDatum());
    }


    @Test
    public void testSetGebuehrNetto() {
        kurs.setGebuehrBrutto(100.0);
        kurs.setMwstProzent(19.0);
        kurs.setGebuehrNetto();
        assertEquals(81.0, kurs.getGebuehrNetto(), 0.001);
    }

    @Test
    public void testSetMwstEuro() {

        kurs.setGebuehrBrutto(100);
        kurs.setMwstProzent(19);
        kurs.setMwstEuro();
        assertEquals(19, kurs.getMwstEuro(), 0.01);
    }

}