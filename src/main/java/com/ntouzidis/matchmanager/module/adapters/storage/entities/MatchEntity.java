package com.ntouzidis.matchmanager.module.adapters.storage.entities;

import static javax.persistence.GenerationType.IDENTITY;

import com.ntouzidis.matchmanager.module.domain.valueobjects.Sport;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "match_date")
  private LocalDate matchDate;

  @Column(name = "match_time")
  private LocalTime matchTime;

  @Column(name = "team_a")
  private String teamA;

  @Column(name = "team_b")
  private String teamB;

  @Column(name = "sport")
  @Enumerated(EnumType.ORDINAL)
  private Sport sport;

  @OneToOne(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
  private MatchOddsEntity odds;


//  public static MatchEntity newFromDomain(Match newMatch) {
//    var newEntity = new MatchEntity();
//    newEntity.description = newMatch.getDescription();
//    newEntity.matchDate = newMatch.getMatchDate();
//    newEntity.matchTime = newMatch.getMatchTime();
//    newEntity.teamA = newMatch.getTeamA();
//    newEntity.teamB = newMatch.getTeamB();
//    newEntity.sport = newMatch.getSport();
//    newEntity.description = newMatch.getDescription();
//
//    var matchOddsEntity = new MatchOddsEntity(null, newEntity, newMatch.getSpecifier(), newMatch.getOdd());
//    newEntity.setOdds(matchOddsEntity);
//
//    return newEntity;
//  }

  public static MatchEntity fromDomain(Match match, Long matchOddsId) {
    var newEntity = new MatchEntity();
    newEntity.id = match.getId();
    newEntity.description = match.getDescription();
    newEntity.matchDate = match.getMatchDate();
    newEntity.matchTime = match.getMatchTime();
    newEntity.teamA = match.getTeamA();
    newEntity.teamB = match.getTeamB();
    newEntity.sport = match.getSport();
    newEntity.description = match.getDescription();

    var matchOddsEntity = new MatchOddsEntity(matchOddsId, newEntity, match.getSpecifier(), match.getOdd());
    newEntity.setOdds(matchOddsEntity);

    return newEntity;
  }

  public Match toDomain() {
    return Match.fromValues(
        this.id,
        this.description,
        this.matchDate,
        this.matchTime,
        this.teamA,
        this.teamB,
        this.sport,
        this.odds.getSpecifier(),
        this.odds.getOdd()
    );
  }

  private void setOdds(MatchOddsEntity odds) {
    if (odds == null) {
      if (this.odds != null) {
        this.odds.setMatch(null);
      }
    }
    else {
      odds.setMatch(this);
    }
    this.odds = odds;
  }
}
