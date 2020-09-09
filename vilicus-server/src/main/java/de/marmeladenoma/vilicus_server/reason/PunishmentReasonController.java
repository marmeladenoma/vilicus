package de.marmeladenoma.vilicus_server.reason;

import de.marmeladenoma.vilicus_server.counter.Counter;
import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public final class PunishmentReasonController {
  private final Datastore datastore;

  private PunishmentReasonController(Datastore datastore) {
    this.datastore = datastore;
  }

  @GetMapping("/reasons")
  Collection<PunishmentReason> allReasons() {
    return datastore.find(PunishmentReason.class).iterator().toList();
  }

  private static final String REASON_NAME = "reason";

  @PostMapping("/reasons")
  PunishmentReason newReason(@RequestBody PunishmentReason newEntry) {
    newEntry.setReasonId(Counter.resolveIncrement(datastore, REASON_NAME));
    return datastore.save(newEntry);
  }

  private static final String ID_NAME = "id";

  @GetMapping("/reasons/{id}")
  PunishmentReason findReason(@PathVariable Long id) {
    return datastore.find(PunishmentReason.class)
      .filter(Filters.eq(ID_NAME, id))
      .first();
  }

  @PutMapping("/reasons/{id}")
  PunishmentReason replaceReason(
    @RequestBody PunishmentReason newReason,
    @PathVariable ObjectId id
  ) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/reasons/{id}")
  void deleteReason(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
