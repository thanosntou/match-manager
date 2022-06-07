package com.ntouzidis.matchmanager.module.application.forms;

import java.time.LocalDate;
import java.time.LocalTime;

public record SearchForm(
    String description,
    String teamA,
    String teamB,
    String sport,
    LocalDate matchDate,
    LocalTime matchTime
) {}
