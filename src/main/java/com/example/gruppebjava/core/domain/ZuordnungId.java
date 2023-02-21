package com.example.gruppebjava.core.domain;

import java.io.Serializable;

public class ZuordnungId implements Serializable {

    protected Long personId;

    protected Long kursId;

    public ZuordnungId(){}


    public ZuordnungId(PersonEntity person, KursEntity kurs){
        this.personId = person.getId();
        this.kursId = kurs.getId();
    }
}
