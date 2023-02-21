package com.example.gruppebjava.core.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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

    public Zuordnung(){}



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
