package de.marmeladenoma.vilicus_server.phase;

import de.marmeladenoma.vilicus_server.reason.PunishmentReason;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
public final class PunishmentPhaseController {

  private final Datastore datastore;

  public PunishmentPhaseController(Datastore datastore) {
    this.datastore = datastore;
  }

  // Mappings

  @GetMapping("/phases")
  Collection<PunishmentPhase> allPhases(@RequestParam Optional<Long> reasonId) {
    Query<PunishmentPhase> query = queryPhases();

    if (reasonId.isEmpty())
      return query.iterator().toList();

    PunishmentReason reason = datastore.find(PunishmentReason.class)
      .filter(Filters.eq(PunishmentReason.FIELD_REASON_ID, reasonId.get()))
      .first();

    if (reason == null)
      return Collections.emptyList();

    query = query.filter(
      Filters.eq(PunishmentPhase.FIELD_REASON, reason.getId())
    );

    return query.iterator().toList();
  }

  @PostMapping("/phases")
  PunishmentPhase newPhase(@RequestBody PunishmentPhase phase) {
    return datastore.save(phase);
  }

  @GetMapping("/phases/{id}")
  PunishmentPhase findPhase(@PathVariable String id) {
    return queryPhase(id).first();
  }

  @PutMapping("/phases/{id}")
  PunishmentPhase replacePhase(
    @RequestBody PunishmentPhase newEntry,
    @PathVariable String id
  ) {
    return queryPhase(id)
      .modify(UpdateOperators.set(newEntry))
      .execute();
  }

  @DeleteMapping("/phases/{id}")
  void deletePhase(@PathVariable String id) {
    queryPhase(id).delete();
  }

  // Queries

  private Query<PunishmentPhase> queryPhases() {
    return datastore.find(PunishmentPhase.class);
  }

  private Query<PunishmentPhase> queryPhase(String id) {
    return queryPhases().filter(Filters.eq(PunishmentPhase.FIELD_ID, id));
  }
}
