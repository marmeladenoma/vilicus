package de.marmeladenoma.vilicus_server.reason;

import de.marmeladenoma.vilicus_server.counter.Counter;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

  @GetMapping("/reasons/{id}")
  PunishmentReason findReason(@PathVariable long id) {
    return queryReason(id).first();
  }

  @PutMapping("/reasons/{id}")
  PunishmentReason replaceReason(
    @RequestBody PunishmentReason newReason,
    @PathVariable long id
  ) {
    return queryReason(id)
      .modify(UpdateOperators.set(newReason))
      .execute();
  }

  @DeleteMapping("/reasons/{id}")
  void deleteReason(@PathVariable long id) {
    queryReason(id).delete();
  }

  private Query<PunishmentReason> queryReasons() {
    return datastore.find(PunishmentReason.class);
  }

  private static final String FIELD_REASON_ID = "id";

  private Query<PunishmentReason> queryReason(long id) {
    return queryReasons()
      .filter(Filters.eq(FIELD_REASON_ID, id));
  }
}
