package com.ntouzidis.matchmanager.module.adapters.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ntouzidis.matchmanager.module.application.forms.MatchForm;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.application.service.MatchService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/api/v1/match",
    produces = APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class MatchController {

  private final MatchService matchService;

  @GetMapping
  public List<Match> getAll() {
    return matchService.getAll();
  }

  @PostMapping
  public Match create(@RequestBody @Valid MatchForm form) {
    return matchService.save(form);
  }

}
