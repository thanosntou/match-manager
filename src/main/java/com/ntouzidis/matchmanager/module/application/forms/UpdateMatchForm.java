package com.ntouzidis.matchmanager.module.application.forms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.commons.lang3.StringUtils;

public record UpdateMatchForm(
    String sport,
    LocalDate matchDate,
    LocalTime matchTime,
    String specifier,
    BigDecimal odd
) {
  public boolean isEmpty() {
    return StringUtils.isBlank(sport)
        && matchDate == null
        && matchTime == null
        && StringUtils.isBlank(specifier)
        && odd == null;
  }
}
