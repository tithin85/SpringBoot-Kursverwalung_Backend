package com.example.gruppebjava.application.rest;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.repo.KursRepo;
import com.example.gruppebjava.core.repo.PersonRepo;
import com.example.gruppebjava.core.service.CreatePdfService;
import com.example.gruppebjava.core.service.PersonService;
import com.example.gruppebjava.core.service.ZuordnungService;
import com.example.gruppebjava.application.rest.PersonController;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private PersonRepo personRepo;

    @Mock
    private KursRepo kursRepo;

    @Mock
    private ZuordnungService zuordnungService;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testGetAllPersons() {

        PersonEntity person1 = new PersonEntity();
        PersonEntity person2 = new PersonEntity();
        List<PersonEntity> persons = Arrays.asList(person1, person2);
        when(personService.findAllPersons()).thenReturn(persons);
        PersonController controller = new PersonController(personService, personRepo, kursRepo, zuordnungService);


        ResponseEntity<List<PersonEntity>> response = controller.getAllPersons();


        verify(personService).findAllPersons();
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(persons, response.getBody());
    }




}
