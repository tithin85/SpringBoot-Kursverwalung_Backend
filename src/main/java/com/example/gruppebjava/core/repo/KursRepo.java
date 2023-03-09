package com.example.gruppebjava.core.repo;

import com.example.gruppebjava.core.domain.KursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KursRepo extends JpaRepository<KursEntity,Long> {

    KursEntity findKursEntityById(Long id);

    List<KursEntity> findAllByStatus(String status);


    void deleteKursEntityById(Long id);


}
