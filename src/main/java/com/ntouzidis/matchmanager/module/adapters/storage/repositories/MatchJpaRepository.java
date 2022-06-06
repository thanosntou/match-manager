package com.ntouzidis.matchmanager.module.adapters.storage.repositories;

import com.ntouzidis.matchmanager.module.adapters.storage.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {
}
