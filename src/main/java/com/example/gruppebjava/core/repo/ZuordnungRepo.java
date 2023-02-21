package com.example.gruppebjava.core.repo;

import com.example.gruppebjava.core.domain.PersonEntity;
import com.example.gruppebjava.core.domain.Zuordnung;
import com.example.gruppebjava.core.domain.ZuordnungId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZuordnungRepo extends JpaRepository<Zuordnung, ZuordnungId> {


}
