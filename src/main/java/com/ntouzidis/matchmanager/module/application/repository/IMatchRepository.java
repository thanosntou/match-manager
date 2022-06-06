package com.ntouzidis.matchmanager.module.application.repository;

import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import java.util.List;

public interface IMatchRepository {

  List<Match> getAll();

  Match save(Match newMatch);

}
