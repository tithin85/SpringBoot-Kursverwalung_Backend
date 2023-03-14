package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;
import com.example.gruppebjava.core.service.CreatePdfService;
import com.example.gruppebjava.core.service.KursService;
import com.example.gruppebjava.core.service.ZuordnungService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/zuordnung")
@CrossOrigin("http://localhost:4200")
public class ZuordnungController {
    private final ZuordnungService zuordnungService;
    private final KursService kursService;
    private final PersonRepo personRepo;
    private final KursRepo kursRepo;
    private final SimpleDateFormat dateOhneZeit = new SimpleDateFormat("dd.MM.yyyy");
    private final String datum = dateOhneZeit.format(new Date());

    public ZuordnungController(ZuordnungService zuordnungService,KursService kursService, PersonRepo personRepo, KursRepo kursRepo) {
        this.zuordnungService = zuordnungService;
        this.kursService=kursService;
        this.personRepo = personRepo;
        this.kursRepo = kursRepo;
    }

    @GetMapping("/all")

    public ResponseEntity<List<Zuordnung>> getAllZuordnungListe() {
        List<Zuordnung> zuordnungList = zuordnungService.findZuordnungListe();
        return new ResponseEntity<>(zuordnungList, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Zuordnung> addAllZuordnungListe(@RequestBody Zuordnung zuordnung) {
        Zuordnung zu = zuordnungService.addPersonAlsTeilnehmer(zuordnung);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(zu.getKursId());
        KursEntity kurs=kursService.findKursById(zu.getKursId());
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);

        return new ResponseEntity<>(zu, HttpStatus.OK);
    }
    @PutMapping("/updateTeilnahme")
    public ResponseEntity<Zuordnung>updateTeilnahme(@RequestBody Zuordnung zuordnung) {
        Zuordnung zu=zuordnungService.updateTeilnahmeStatus(zuordnung);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(zu.getKursId());
        KursEntity kurs=kursService.findKursById(zu.getKursId());
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);
        return new ResponseEntity<>(zu, HttpStatus.OK);
    }




    @DeleteMapping("/delete/{personId}/{kursId}")
    public ResponseEntity<?> deleteById(@PathVariable("personId") long personId, @PathVariable("kursId") long kursId) {
        zuordnungService.deleteZuordnung(personId, kursId);
        int teilnehmerZahl=zuordnungService.getAktuelleTeilnehmerZahl(kursId);
        KursEntity kurs=kursService.findKursById(kursId);
        kurs.setAktuelleTnZahl(teilnehmerZahl);
        kurs.setFreiePlaetze();
        kursService.updateKurs(kurs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/personAlsTeilnehmer/{personId}")
    public ResponseEntity<List<KursEntity>> getTeilnehmeKurse(@PathVariable("personId") long personId) {
        List<KursEntity> result = zuordnungService.personalsTeilnehmer(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/personAlsInteressanter/{personId}")
    public ResponseEntity<List<KursEntity>> getInteressierteKurse(@PathVariable("personId") long personId) {
        List<KursEntity> result = zuordnungService.personalsInteressanter(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/kursTeilnehmer/{kursId}")
    public ResponseEntity<List<PersonEntity>> getKursTeilnehmer(@PathVariable("kursId") long kursId) {
        List<PersonEntity> result = zuordnungService.teilnehmerListe(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/kursInteressanter/{kursId}")
    public ResponseEntity<List<PersonEntity>> getKursInteressanter(@PathVariable("kursId") long kursId) {
        List<PersonEntity> result = zuordnungService.interessanterListe(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/remainingKurs/{personId}")
    public ResponseEntity<List<KursEntity>>getRemainingKurs(@PathVariable("personId") long personId){
        List<KursEntity> result = zuordnungService.remainingKurses(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping("/remainingPersonen/{kursId}")
    public ResponseEntity<List<PersonEntity>>getRemainingPersonen(@PathVariable("kursId") long kursId){
        List<PersonEntity> result = zuordnungService.remainingPersonen(kursId);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/pdf-anwesenheitsliste/{kursId}")
    void pdfKursListe(HttpServletResponse response, @PathVariable("kursId") long kursId) throws IOException {
        CreatePdfService pdfKurs = new CreatePdfService(personRepo, kursRepo, zuordnungService);
        try{
            pdfKurs.createAnwesenheitslistePdf(kursId, datum);
        }catch(IOException e){
            System.out.println("Fehler beim Schreiben der Kurs-Pdf-Datei!");
        }
        response.setContentType("application/pdf");
        String pdfname = "Anwesenheitsliste_"
                + pdfKurs.aktuellerKurs(kursId).getName().replace(" ", "_")
                + "_" + datum + ".pdf";
        response.setHeader("Content-Disposition", "attachment; filename=pdfname");
        FileInputStream inputStream = new FileInputStream("src/main/resources/static/download/" + pdfname);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
