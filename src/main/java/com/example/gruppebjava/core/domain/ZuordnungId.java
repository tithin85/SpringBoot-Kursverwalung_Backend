package com.example.gruppebjava.core.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


public class ZuordnungId implements Serializable {

    protected Long personId;

    protected Long kursId;

    public ZuordnungId(){}


    public ZuordnungId(Long personId, Long kursId){
        this.personId = personId;
        this.kursId = kursId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZuordnungId that = (ZuordnungId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(kursId, that.kursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, kursId);
    }
}
