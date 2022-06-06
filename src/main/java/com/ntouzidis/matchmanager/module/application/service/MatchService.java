package com.ntouzidis.matchmanager.module.application.service;

import com.ntouzidis.matchmanager.module.application.forms.CreateMatchForm;
import com.ntouzidis.matchmanager.module.application.forms.UpdateMatchForm;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.domain.valueobjects.Sport;
import com.ntouzidis.matchmanager.module.application.repository.IMatchRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MatchService {

  private final IMatchRepository matchRepository;

  @Transactional(readOnly = true)
  public List<Match> getAll() {
    return matchRepository.getAll();
  }

  @Transactional
  public Match save(@NonNull CreateMatchForm form) {

    var newMatch = Match.createNew(
        form.matchDate(),
        form.matchTime(),
        form.teamA(),
        form.teamB(),
        Sport.fromText(form.sport()),
        form.specifier(),
        form.odd());

    validateMatchDate(newMatch.getDescription(), newMatch.getMatchDate());

    return matchRepository.save(newMatch);
  }

  @Transactional
  public Match update(@NonNull Long id, @NonNull UpdateMatchForm form) {
    if (form.isEmpty()) {
      throw new IllegalArgumentException("Form is empty");
    }
    var match = matchRepository.getById(id);

    Optional.ofNullable(form.matchDate()).ifPresent(newDate -> {
      if (!match.getMatchDate().isEqual(newDate)) {
        validateMatchDate(match.getDescription(), form.matchDate());
        match.setMatchDate(newDate);
      }
    });
    Optional.ofNullable(form.sport()).ifPresent(match::setSport);
    Optional.ofNullable(form.matchTime()).ifPresent(match::setMatchTime);
    Optional.ofNullable(form.specifier()).ifPresent(match::setSpecifier);
    Optional.ofNullable(form.odd()).ifPresent(match::setOdd);

    return matchRepository.update(match);
  }

  @Transactional
  public void delete(@NonNull Long id) {
    matchRepository.delete(id);
  }

  private void validateMatchDate(String description, LocalDate matchDate) {
    if (matchRepository
        .findByDescriptionAndDate(description, matchDate)
        .isPresent()) {
      throw new IllegalArgumentException(String.format(
          "There is already a planned game on %s for %s.", matchDate, description));
    }
  }

}
