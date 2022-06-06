package com.ntouzidis.matchmanager.module.application.service;

import com.ntouzidis.matchmanager.module.application.forms.MatchForm;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.domain.valueobjects.Sport;
import com.ntouzidis.matchmanager.module.application.repository.IMatchRepository;
import java.util.List;
import lombok.AllArgsConstructor;
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
  public Match save(MatchForm form) {

    var newMatch = Match.createNew(
        form.getDescription(),
        form.getMatchDate(),
        form.getMatchTime(),
        form.getTeamA(),
        form.getTeamB(),
        Sport.fromText(form.getSport()),
        form.getSpecifier(),
        form.getOdd());

    return matchRepository.save(newMatch);
  }

}
