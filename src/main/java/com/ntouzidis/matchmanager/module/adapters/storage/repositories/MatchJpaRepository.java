package com.ntouzidis.matchmanager.module.adapters.storage.repositories;

import com.ntouzidis.matchmanager.module.adapters.storage.entities.MatchEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {

  Optional<MatchEntity> findByDescriptionAndMatchDate(String description, LocalDate matchDate);
}
