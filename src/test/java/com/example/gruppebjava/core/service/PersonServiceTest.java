package com.example.gruppebjava.core.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.repo.PersonRepo;
import com.example.gruppebjava.core.repo.ZuordnungRepo;
import com.example.gruppebjava.core.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @Mock
    private ZuordnungRepo zuordnungRepo;

    @Test
    public void testAddPersonValidEmail() {

        PersonEntity person = new PersonEntity();
        person.setEmail("test@example.com");
        when(personRepo.save(any(PersonEntity.class))).thenReturn(person);
        PersonService service = new PersonService(personRepo, zuordnungRepo);


        PersonEntity result = service.addPerson(person);


        verify(personRepo).save(person);
        assertSame(person, result);
    }

    @Test
    public void testAddPersonInvalidEmail() {

        PersonEntity person = new PersonEntity();
        person.setEmail("invalid-email");
        PersonService service = new PersonService(personRepo, zuordnungRepo);


        PersonEntity result = service.addPerson(person);


        verify(personRepo, never()).save(any(PersonEntity.class));
        assertNull(result);
    }


}
