package com.ntouzidis.matchmanager.module.domain.valueobjects;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sport {
  FOOTBALL("Football"),
  BASKETBALL("Basketball");

  private String value;

  public static Sport fromText(String text) {
    return Arrays.stream(Sport.values())
        .filter(s -> s.value.equalsIgnoreCase(text))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(String.format("Sport with value [%s] not found", text)));
  }
}
