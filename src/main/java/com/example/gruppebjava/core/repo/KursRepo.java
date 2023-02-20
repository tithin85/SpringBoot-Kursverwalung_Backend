package com.example.gruppebjava.core.repo;

import com.example.gruppebjava.core.domain.KursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KursRepo extends JpaRepository<KursEntity,Long> {
}
