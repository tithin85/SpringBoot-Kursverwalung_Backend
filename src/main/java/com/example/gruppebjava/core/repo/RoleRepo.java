package com.example.gruppebjava.core.repo;

import com.example.gruppebjava.core.domain.ERole;
import com.example.gruppebjava.core.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
