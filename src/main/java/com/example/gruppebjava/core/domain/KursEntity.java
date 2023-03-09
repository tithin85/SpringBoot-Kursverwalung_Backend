package com.example.gruppebjava.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank(message = "Kurs name muss mindestens einzeichen.")
    @Column(nullable = false)
    @Size(min = 1, max = 64)
    private String name;
    @Min(1)
    @Column(nullable = false)
    private int anzahlTage;
    @Min(1)
    @Max(7)
    @Column(nullable = false)
    private int wieOftinWoche;
    @Future
    @DateTimeFormat
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDatum;

    @Temporal(TemporalType.DATE)
    @Column
    private Date endeDatum;
    @Column
    private int aktuelleTnZahl;
    @Min(1)
    @Column(nullable = false)
    private int minTnZahl;
    @Min(1)
    @Column(nullable = false)
    private int maxTnZahl;
    @Column
    private int freiePlaetze;
    @Column(nullable = false)
    @Digits(integer = 10, fraction = 2)
    private double gebuehrBrutto;
    @Column
    private double gebuehrNetto;
    @Column
    private double mwstEuro;
    @Column(nullable = false)
    private double mwstProzent;
    @Column
    private String kursBeschreibung;
    @Column(nullable = false)
    private String status;


    public KursEntity() {
    }

    public KursEntity(String name, int anzahlTage, int wieOftinWoche, Date startDatum,
                      int minTnZahl, int maxTnZahl, double gebuehrBrutto, double mwstProzent,
                      String kursBeschreibung, String status) {
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

    public int getAnzahlTage() {
        return anzahlTage;
    }

    public void setAnzahlTage(int anzahlTage) {
        this.anzahlTage = anzahlTage;
    }

    public int getWieOftinWoche() {
        return wieOftinWoche;
    }

    public void setWieOftinWoche(int wieOftinWoche) {
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

    public void setEndeDatum() {
        long dat = startDatum.getTime() + ((Math.round((float) getAnzahlTage() / getWieOftinWoche())) * 7 * 86400000L);
        this.endeDatum = new Date(dat);

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

    public void setFreiePlaetze() {
        this.freiePlaetze=this.maxTnZahl-this.aktuelleTnZahl;
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

    public void setGebuehrNetto() {
        this.gebuehrNetto = getGebuehrBrutto()-(getGebuehrBrutto() * (getMwstProzent() / 100));
    }

    public double getMwstProzent() {
        return mwstProzent;
    }

    public void setMwstProzent(double mwstProzent) {
        this.mwstProzent = mwstProzent;
    }


    public double getMwstEuro() {
        return mwstEuro;
    }

    public void setMwstEuro() {
        this.mwstEuro=getGebuehrBrutto() * (getMwstProzent()/100);
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
               '}';
    }



}
