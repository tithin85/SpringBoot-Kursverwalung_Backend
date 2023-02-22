package com.example.gruppebjava.core.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(ZuordnungId.class)

public class Zuordnung implements Serializable {

    @Id
    private Long personId;
    @Id
    private Long kursId;

    private boolean teilnehmer;

    public Zuordnung(Long person,Long kurs,boolean teilnehmer){
        this.personId=person;
        this.kursId=kurs;
        this.teilnehmer=teilnehmer;
    }
    public Zuordnung(){}

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getKursId() {
        return kursId;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public boolean isTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(boolean teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    @Override
    public String toString() {
        return "Zuordnung{" +
                "personId=" + personId +
                ", kursId=" + kursId +
                ", teilnehmer=" + teilnehmer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zuordnung zuordnung)) return false;
        return isTeilnehmer() == zuordnung.isTeilnehmer() && Objects.equals(personId, zuordnung.personId) && Objects.equals(kursId, zuordnung.kursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, kursId, isTeilnehmer());
    }
}
