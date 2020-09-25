package de.marmeladenoma.vilicus_server.reason;

import de.marmeladenoma.vilicus_server.counter.Counter;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public final class PunishmentReasonController {
  private final Datastore datastore;
  private final Counter counter;

  private PunishmentReasonController(Datastore datastore, Counter counter) {
    this.datastore = datastore;
    this.counter = counter;
  }

  @GetMapping("/reasons")
  Collection<PunishmentReason> allReasons() {
    return queryReasons().iterator().toList();
  }

  private static final String REASON_NAME = "reason";

  @PostMapping("/reasons")
  PunishmentReason newReason(@RequestBody PunishmentReason newEntry) {
    var nextId = counter.resolveIncrement(REASON_NAME);
    newEntry.setReasonId(nextId);
    return datastore.save(newEntry);
  }

  @GetMapping("/reasons/{reasonId}")
  ResponseEntity<PunishmentReason> findReason(@PathVariable long reasonId) {
    var reason = queryReason(reasonId).first();
    return reason == null
      ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
      : new ResponseEntity<>(reason, HttpStatus.OK);
  }

  @DeleteMapping("/reasons/{reasonId}")
  ResponseEntity<Void> deleteReason(@PathVariable long reasonId) {
    var reason = queryReason(reasonId).delete();
    return reason.wasAcknowledged()
      ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/reasons/{reasonId}/phases")
  ResponseEntity<List<PunishmentPhase>> findPhasesInReason(
    @PathVariable long reasonId
  ) {
    var reason = queryReason(reasonId).first();
    return reason == null
      ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
      : new ResponseEntity<>(reason.getPhases(), HttpStatus.OK);
  }

  private static final String FIELD_PHASES = "phases";

  @PostMapping("/reasons/{reasonId}/phases")
  ResponseEntity<PunishmentPhase> newPhaseInReason(
    @RequestBody PunishmentPhase phase,
    @PathVariable long reasonId
  ) {
    var queryReason = queryReason(reasonId);
    var reason = queryReason.first();
    if (reason == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    var phases = getPhasesOrEmptyList(queryReason.first());
    phases.add(phase);
    queryReason.update(UpdateOperators.set(FIELD_PHASES, phases))
      .execute();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/reasons/{reasonId}/phases/{phaseIndex}")
  ResponseEntity<PunishmentPhase> insertPhaseInReason(
    @RequestBody PunishmentPhase phase,
    @PathVariable long reasonId,
    @PathVariable int phaseIndex
  ) {
    var queryReason = queryReason(reasonId);
    var reason = queryReason(reasonId).first();
    if (reason == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    var phases = getPhasesOrEmptyList(reason);
    if (phaseIndex < 0 || phaseIndex > phases.size())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    phases.add(phaseIndex, phase);
    queryReason.update(UpdateOperators.set(FIELD_PHASES, phases))
      .execute();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/reasons/{reasonId}/phases/{phaseIndex}")
  ResponseEntity<PunishmentPhase> findPhaseInReason(
    @PathVariable long reasonId,
    @PathVariable int phaseIndex
  ) {
    var reason = queryReason(reasonId).first();
    if (reason == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    var phase = reason.getPhases().get(phaseIndex);
    return phase == null
      ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
      : new ResponseEntity<>(phase, HttpStatus.OK);
  }

  @DeleteMapping("/reasons/{reasonId}/phases/{phaseIndex}")
  ResponseEntity<Void> deletePhaseInReason(
    @PathVariable long reasonId,
    @PathVariable int phaseIndex
  ) {
    var queryReason = queryReason(reasonId);
    var reason = queryReason.first();
    if (reason == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    var phases = reason.getPhases();
    if (phaseIndex < 0 || phaseIndex >= phases.size()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    phases.remove(phaseIndex);
    queryReason.update(UpdateOperators.set(FIELD_PHASES, phases)).execute();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private Query<PunishmentReason> queryReasons() {
    return datastore.find(PunishmentReason.class);
  }

  private static final String FIELD_REASON_ID = "reasonId";

  private Query<PunishmentReason> queryReason(long id) {
    return queryReasons()
      .filter(Filters.eq(FIELD_REASON_ID, id));
  }

  private List<PunishmentPhase> getPhasesOrEmptyList(PunishmentReason reason) {
    var phases = reason.getPhases();
    if (phases == null)
      phases = new ArrayList<>();
    return phases;
  }
}
