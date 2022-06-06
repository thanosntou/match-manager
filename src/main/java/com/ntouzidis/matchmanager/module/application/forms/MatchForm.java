package com.ntouzidis.matchmanager.module.application.forms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class MatchForm {

  String description;

  @NotNull LocalDate matchDate;

  @NotNull LocalTime matchTime;

  @NotBlank String teamA;

  @NotBlank String teamB;

  @NotBlank String sport;

  @NotBlank String specifier;

  @NotNull BigDecimal odd;

}
