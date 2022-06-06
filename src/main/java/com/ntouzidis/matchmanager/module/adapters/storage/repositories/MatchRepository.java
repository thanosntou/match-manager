package com.ntouzidis.matchmanager.module.adapters.storage.repositories;

import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.adapters.storage.entities.MatchEntity;
import com.ntouzidis.matchmanager.module.application.repository.IMatchRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MatchRepository implements IMatchRepository {

  private final MatchJpaRepository matchJpaRepository;

  public List<Match> getAll() {
    return matchJpaRepository.findAll()
        .stream()
        .map(MatchEntity::toDomain)
        .toList();
  }

  public Match save(Match newMatch) {
    var newMatchEntity = MatchEntity.fromDomain(newMatch, null);
    var savedMatch = matchJpaRepository.save(newMatchEntity);
    return savedMatch.toDomain();
  }

  @Override
  public Match update(Match match) {
    var matchOddsId = matchJpaRepository.getById(match.getId()).getOdds().getId();
    var matchEntity = MatchEntity.fromDomain(match, matchOddsId);
    var savedMatch = matchJpaRepository.save(matchEntity);
    return savedMatch.toDomain();
  }

  @Override
  public Match getById(Long id) {
    return matchJpaRepository.findById(id).map(MatchEntity::toDomain)
        .orElseThrow(() -> new RuntimeException(String.format("Match with id [%s] not found", id)));
  }

  @Override
  public Optional<Match> findById(Long id) {
    return matchJpaRepository.findById(id).map(MatchEntity::toDomain);
  }

  @Override
  public Optional<Match> findByDescriptionAndDate(String description, LocalDate matchDate) {
    return matchJpaRepository.findByDescriptionAndMatchDate(description, matchDate)
        .map(MatchEntity::toDomain);
  }

  public void delete(Long id) {
    matchJpaRepository.findById(id).ifPresent(matchJpaRepository::delete);
  }

}
