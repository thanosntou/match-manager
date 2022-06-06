package com.ntouzidis.matchmanager.module.application.repository;

import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMatchRepository {

  List<Match> getAll();

  Match save(Match newMatch);

  Match update(Match newMatch);

  Match getById(Long id);

  Optional<Match> findById(Long id);

  Optional<Match> findByDescriptionAndDate(String description, LocalDate matchDate);

  void delete(Long id);

}
