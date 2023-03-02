package com.example.gruppebjava.core.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "anrede", length = 100)
    private String anrede;
    @Column(name = "titel", length = 100)
    private String titel;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "vorname", length = 100, nullable = false)
    private String vorname;

    @Column(name = "strasse", length = 100, nullable = false)
    private String strasse;

    @Column(name = "plz", length = 100, nullable = false)
    private String plz;

    @Column(name = "ort", length = 100, nullable = false)
    private String ort;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "telefon", length = 100)
    private String telefon;

    public PersonEntity(String name, String vorname, String strasse, String plz, String ort, String email) {

        this.name = name;
        this.vorname = vorname;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.email = email;

    }

    public PersonEntity() {

    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getAnrede() { return anrede; }

    public void setAnrede(String anrede) { this.anrede = anrede; }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonEntity that)) return false;
        return Objects.equals(getTitel(), that.getTitel()) && Objects.equals(getName(), that.getName()) && Objects.equals(getVorname(), that.getVorname()) && Objects.equals(getStrasse(), that.getStrasse()) && Objects.equals(getPlz(), that.getPlz()) && Objects.equals(getOrt(), that.getOrt()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getTelefon(), that.getTelefon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitel(), getName(), getVorname(), getStrasse(), getPlz(), getOrt(), getEmail(), getTelefon());
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", anrede='" + anrede + '\'' +
                ", titel='" + titel + '\'' +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}

