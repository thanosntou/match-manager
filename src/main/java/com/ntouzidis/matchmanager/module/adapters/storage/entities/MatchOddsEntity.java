package com.ntouzidis.matchmanager.module.adapters.storage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "match_odds")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchOddsEntity implements Serializable {

  @Id
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @MapsId
  @JsonIgnore
  @Setter(value = AccessLevel.PROTECTED)
  private MatchEntity match;

  @Column(name = "specifier")
  private String specifier;

  @Column(name = "odd")
  private BigDecimal odd;

}
