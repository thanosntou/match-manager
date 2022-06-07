package com.ntouzidis.matchmanager.module.adapters.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ntouzidis.matchmanager.module.application.forms.CreateMatchForm;
import com.ntouzidis.matchmanager.module.application.forms.SearchForm;
import com.ntouzidis.matchmanager.module.application.forms.UpdateMatchForm;
import com.ntouzidis.matchmanager.module.domain.aggregates.Match;
import com.ntouzidis.matchmanager.module.application.service.MatchService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/search")
  public List<Match> search(@RequestBody @Valid SearchForm form) {
    return matchService.search(form);
  }

  @GetMapping
  public List<Match> getAll() {
    return matchService.getAll();
  }

  @PostMapping
  public Match create(@RequestBody @Valid CreateMatchForm form) {
    return matchService.save(form);
  }

  @PostMapping("/{id}")
  public Match update(@PathVariable Long id, @RequestBody @Valid UpdateMatchForm form) {
    return matchService.update(id, form);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    matchService.delete(id);
  }

}
