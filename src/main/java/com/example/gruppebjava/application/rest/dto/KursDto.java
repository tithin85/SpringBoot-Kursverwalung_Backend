package com.example.gruppebjava.application.rest.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KursDto {
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
    @Min(1)
    private String anzahlTage;
    @Min(1)
    @Max(7)
    private String wieOftinWoche;
    @Past
    @DateTimeFormat
    private Date startDatum;
    private Date endeDatum;
    private int aktuelleTnZahl;
    @Min(1)
    private int minTnZahl;
    @Min(1)
    private int maxTnZahl;
    private int freiePlaetze;
    @Digits(integer = 10, fraction = 2)
    private double gebuehrBrutto;
    private double gebuehrNetto;
    private double mwstEuro;
    @Min(1)
    private double mwstProzent;
    @NotBlank
    @Size(min = 1, max = 1000)
    private String kursBeschreibung;
    @NotBlank
    @Size(min = 1, max = 1000)
    private String status;

    private String kursCode;


}
