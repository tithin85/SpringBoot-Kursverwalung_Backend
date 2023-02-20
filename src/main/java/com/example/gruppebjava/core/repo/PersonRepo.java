package com.example.gruppebjava.core.repo;

import com.example.gruppebjava.core.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonEntity, Long> {

    void deletePersonEntityById(Long id);

    PersonEntity findPersonEntityById(Long id);


}
