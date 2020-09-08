package de.marmeladenoma.vilicus.reason;

import de.marmeladenoma.vilicus.counter.Counter;
import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PunishmentReasonController {

  private Datastore datastore;

  public PunishmentReasonController(Datastore datastore) {
    this.datastore = datastore;
  }

  @GetMapping("/reasons")
  List<PunishmentReason> allReasons() {
    return datastore.find(PunishmentReason.class).iterator().toList();
  }

  @PostMapping("/reasons")
  PunishmentReason newReason(@RequestBody PunishmentReason newEntry) {
    newEntry.setReasonId(Counter.next(datastore, "reason"));
    return datastore.save(newEntry);
  }

  @GetMapping("/reasons/{id}")
  PunishmentReason findReason(@PathVariable Long id) {
    return datastore.find(PunishmentReason.class)
      .filter(Filters.eq("id", id))
      .first();
  }

  @PutMapping("/reasons/{id}")
  PunishmentReason replaceReason(@PathVariable ObjectId id, @RequestBody PunishmentReason newReason) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/reasons/{id}")
  void deleteReason(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
