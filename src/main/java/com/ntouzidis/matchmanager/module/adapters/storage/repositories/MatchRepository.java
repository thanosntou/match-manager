package com.ntouzidis.matchmanager.module.adapters.storage.repositories;

import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.adapters.storage.entities.MatchEntity;
import com.ntouzidis.matchmanager.module.application.repository.IMatchRepository;
import java.util.List;
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
    var newMatchEntity = MatchEntity.fromDomain(newMatch);
    var savedMatch = matchJpaRepository.save(newMatchEntity);
    return savedMatch.toDomain();
  }

}
