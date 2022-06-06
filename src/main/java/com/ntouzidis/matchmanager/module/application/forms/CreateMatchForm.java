package com.ntouzidis.matchmanager.module.application.forms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateMatchForm(
    @NotBlank String teamA,
    @NotBlank String teamB,
    @NotBlank String sport,
    @NotNull LocalDate matchDate,
    @NotNull LocalTime matchTime,
    @NotBlank String specifier,
    @NotNull BigDecimal odd
) {}
