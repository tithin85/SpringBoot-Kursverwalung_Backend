package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.KursEntity;
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
import java.util.List;


@RestController
@RequestMapping("/kurs")
@CrossOrigin("http://localhost:4200")

public class KursController {
    private final KursService kursService;
    private final PersonRepo personRepo;
    private final KursRepo kursRepo;
    private final ZuordnungService zuordnungService;

    public KursController(KursService kursService, PersonRepo personRepo, KursRepo kursRepo, ZuordnungService zuordnungService) {
        this.kursService = kursService;
        this.personRepo = personRepo;
        this.kursRepo = kursRepo;
        this.zuordnungService = zuordnungService;
    }


   @PostMapping(value = "/add")
    public ResponseEntity<KursEntity> addKurs(@RequestBody KursEntity kurs){
        KursEntity newKurs =kursService.addKurs(kurs);
        return  new ResponseEntity<>(newKurs, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<KursEntity>> getAllKurse(){
       List<KursEntity> kursEntityList=kursService.findAllKurse();
        return  new ResponseEntity<>(kursEntityList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<KursEntity> getKursById(@PathVariable("id") Long id){
        KursEntity kurs=kursService.findKursById(id);
        return  new ResponseEntity<>(kurs, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<List<KursEntity>> updateKurs(@RequestBody KursEntity kurs){
        KursEntity updatetKurs = kursService.updateKurs(kurs);
        return getAllKurse();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<KursEntity>> deleteKurs(@PathVariable("id") Long id){
        //<?> return anything
        kursService.deleteKurs(id);
        return getAllKurse();
    }

    @GetMapping("/pdf-kursliste")
    void pdfKursListe(HttpServletResponse response) throws IOException {
        CreatePdfService pdfKurs = new CreatePdfService(personRepo, kursRepo, zuordnungService);
        try{
            pdfKurs.createKursListePdf();
        }catch(IOException e){
            System.out.println("Fehler beim Schreiben der Kurs-Pdf-Datei!");
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Kursliste.pdf\"");
        FileInputStream inputStream = new FileInputStream("src/main/resources/static/download/Kursliste.pdf");
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

}
