package com.ntouzidis.matchmanager.module.adapters.storage.repositories;

import com.ntouzidis.matchmanager.module.adapters.storage.entities.MatchEntity;
import com.ntouzidis.matchmanager.module.application.forms.SearchForm;
import com.ntouzidis.matchmanager.module.application.repository.IMatchRepository;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.domain.valueobjects.Sport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MatchRepository implements IMatchRepository {

  private final MatchJpaRepository matchJpaRepository;

  private final EntityManager entityManager;

  @Override
  public List<Match> search(SearchForm form) {
    var sport = Optional.ofNullable(form.sport()).map(Sport::fromText).orElse(null);

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<MatchEntity> cr = cb.createQuery(MatchEntity.class);
    Root<MatchEntity> root = cr.from(MatchEntity.class);

    var predicates = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(form.description())) {
      predicates.add(cb.equal(root.get("description"), form.description()));
    }
    if (StringUtils.isNotBlank(form.teamA())) {
      predicates.add(cb.equal(root.get("teamA"), form.teamA()));
    }
    if (StringUtils.isNotBlank(form.teamB())) {
      predicates.add(cb.equal(root.get("teamB"), form.teamB()));
    }
    if (sport != null) {
      predicates.add(cb.equal(root.get("sport"), sport));
    }
    if (form.matchDate() != null) {
      predicates.add(cb.equal(root.get("matchDate"), form.matchDate()));
    }
    if (form.matchTime() != null) {
      predicates.add(cb.equal(root.get("matchTime"), form.matchTime()));
    }
    Predicate[] list = predicates.toArray(new Predicate[0]);
    cr.select(root).where(list);

    return entityManager.createQuery(cr).getResultList()
        .stream()
        .map(MatchEntity::toDomain)
        .toList();

  }

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
  public Optional<Match> findByDescriptionAndDate(String description, LocalDate matchDate) {
    return matchJpaRepository.findByDescriptionAndMatchDate(description, matchDate)
        .map(MatchEntity::toDomain);
  }

  public void delete(Long id) {
    matchJpaRepository.findById(id).ifPresent(matchJpaRepository::delete);
  }

}
