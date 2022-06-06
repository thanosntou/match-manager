package com.ntouzidis.matchmanager.module.domain.aggregates;

import com.google.common.base.Preconditions;
import com.ntouzidis.matchmanager.module.domain.valueobjects.Sport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Match {

  private Long id;
  private String description;
  private LocalDate matchDate;
  private LocalTime matchTime;
  private String teamA;
  private String teamB;
  private Sport sport;
  private String specifier;
  private BigDecimal odd;

  /**
   * Method for initializing a new domain object, ready to be persisted.
   * Add all validations here.
   * @return The domain object
   */
  public static Match createNew(
      String description,
      @NonNull LocalDate matchDate,
      @NonNull LocalTime matchTime,
      @NonNull String teamA,
      @NonNull String teamB,
      @NonNull Sport sport,
      @NonNull String specifier,
      @NonNull BigDecimal odd) {

    Preconditions.checkArgument(!teamA.equalsIgnoreCase(teamB), "The teams must be different");
    Preconditions.checkArgument(odd.intValue() > 0, "The odd must be bigger than zero");
    Preconditions.checkArgument(matchDate.isAfter(LocalDate.now()), "The match date must be tomorrow or later");

    return fromValues(null, description, matchDate, matchTime, teamA, teamB, sport, specifier, odd);
  }

  /**
   * Method for converting a fetched entity to domain.
   * No business rules required, since the data are already stored,
   * and we are confident for their validity.
   * @return The domain object
   */
  public static Match fromValues(
      Long id, String description,
      LocalDate matchDate, LocalTime matchTime,
      String teamA, String teamB,
      Sport sport, String specifier, BigDecimal odd) {

    var entity = new Match();
    entity.id = id;
    entity.description = description;
    entity.matchDate = matchDate;
    entity.matchTime = matchTime;
    entity.teamA = teamA;
    entity.teamB = teamB;
    entity.sport = sport;
    entity.specifier = specifier;
    entity.odd = odd;
    return entity;
  }

}
