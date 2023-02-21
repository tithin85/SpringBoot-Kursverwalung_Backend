package com.example.gruppebjava.core.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="kurs")
public class KursEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String anzahlTage;
    @Column(nullable = false)
    private String wieOftinWoche;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDatum;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endeDatum;
    @Column
    private int aktuelleTnZahl;
    @Column(nullable = false)
    private int minTnZahl;
    @Column(nullable = false)
    private int maxTnZahl;
    @Column
    private int freiePlaetze;
    @Column(nullable = false)
    private double gebuehrBrutto;
    @Column
    private double gebuehrNetto;
    @Column(nullable = false)
    private double mwstEuro;
    @Column(nullable = false)
    private double mwstProzent;
    @Column
    private String kursBeschreibung;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false,updatable = false)
    private String kursCode;

    public KursEntity() {
    }

    public KursEntity(String name, String anzahlTage, String wieOftinWoche, Date startDatum,
                      int minTnZahl, int maxTnZahl, double gebuehrBrutto, double mwstProzent,
                      String kursBeschreibung, String status,String kursCode) {
        this.name = name;
        this.anzahlTage = anzahlTage;
        this.wieOftinWoche = wieOftinWoche;
        this.startDatum = startDatum;
        this.minTnZahl = minTnZahl;
        this.maxTnZahl = maxTnZahl;
        this.gebuehrBrutto = gebuehrBrutto;
        this.mwstProzent = mwstProzent;
        this.kursBeschreibung = kursBeschreibung;
        this.status = status;
        this.kursCode=kursCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnzahlTage() {
        return anzahlTage;
    }

    public void setAnzahlTage(String anzahlTage) {
        this.anzahlTage = anzahlTage;
    }

    public String getWieOftinWoche() {
        return wieOftinWoche;
    }

    public void setWieOftinWoche(String wieOftinWoche) {
        this.wieOftinWoche = wieOftinWoche;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEndeDatum() {
        return endeDatum;
    }

    public void setEndeDatum(Date endeDatum) {
        this.endeDatum = endeDatum;
    }

    public int getAktuelleTnZahl() {
        return aktuelleTnZahl;
    }

    public void setAktuelleTnZahl(int aktuelleTnZahl) {
        this.aktuelleTnZahl = aktuelleTnZahl;
    }

    public int getMinTnZahl() {
        return minTnZahl;
    }

    public void setMinTnZahl(int minTnZahl) {
        this.minTnZahl = minTnZahl;
    }

    public int getMaxTnZahl() {
        return maxTnZahl;
    }

    public void setMaxTnZahl(int maxTnZahl) {
        this.maxTnZahl = maxTnZahl;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public double getGebuehrBrutto() {
        return gebuehrBrutto;
    }

    public void setGebuehrBrutto(double gebuehrBrutto) {
        this.gebuehrBrutto = gebuehrBrutto;
    }

    public double getGebuehrNetto() {
        return gebuehrNetto;
    }

    public void setGebuehrNetto(double gebuehrNetto) {
        this.gebuehrNetto = gebuehrNetto;
    }

    public double getMwstEuro() {
        return mwstEuro;
    }

    public void setMwstEuro(double mwstEuro) {
        this.mwstEuro = mwstEuro;
    }

    public double getMwstProzent() {
        return mwstProzent;
    }

    public void setMwstProzent(double mwstProzent) {
        this.mwstProzent = mwstProzent;
    }

    public String getKursBeschreibung() {
        return kursBeschreibung;
    }

    public void setKursBeschreibung(String kursBeschreibung) {
        this.kursBeschreibung = kursBeschreibung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKursCode() {
        return kursCode;
    }

    public void setKursCode(String kursCode) {
        this.kursCode = kursCode;
    }

    @Override
    public String toString() {
        return "KursEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", anzahlTage='" + anzahlTage + '\'' +
                ", wieOftinWoche='" + wieOftinWoche + '\'' +
                ", startDatum=" + startDatum +
                ", endeDatum=" + endeDatum +
                ", aktuelleTnZahl=" + aktuelleTnZahl +
                ", minTnZahl=" + minTnZahl +
                ", maxTnZahl=" + maxTnZahl +
                ", freiePlaetze=" + freiePlaetze +
                ", gebuehrBrutto=" + gebuehrBrutto +
                ", gebuehrNetto=" + gebuehrNetto +
                ", mwstEuro=" + mwstEuro +
                ", mwstProzent=" + mwstProzent +
                ", kursBeschreibung='" + kursBeschreibung + '\'' +
                ", status='" + status + '\'' +
                ", kursCode='" + kursCode + '\'' +
                '}';
    }


}
