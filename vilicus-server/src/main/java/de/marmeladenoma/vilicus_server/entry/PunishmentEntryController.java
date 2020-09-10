package de.marmeladenoma.vilicus_server.entry;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public final class PunishmentEntryController {
  private final Datastore datastore;

  private PunishmentEntryController(Datastore datastore) {
    this.datastore = datastore;
  }

  private static final String FIELD_USER_ID = "userId";
  private static final String FIELD_ACTIVE_ID = "active";

  @GetMapping("/entries")
  Collection<PunishmentEntry> allEntries(
    @RequestParam Optional<String> userId,
    @RequestParam Optional<Boolean> active) {
    Query<PunishmentEntry> query = datastore.find(PunishmentEntry.class);
    if (userId.isPresent()) {
      query = query.filter(Filters.eq(FIELD_USER_ID, userId.get()));
    }
    if(active.isPresent()) {
      query = query.filter(Filters.eq(FIELD_ACTIVE_ID, active.get()));
    }

    return query.iterator().toList();
  }

  @PostMapping("/entries")
  PunishmentEntry newEntry(@RequestBody PunishmentEntry entry) {
    return datastore.save(entry);
  }

  @GetMapping("/entries/{id}")
  PunishmentEntry findEntry(@PathVariable String id) {
    return queryEntry(id).first();
  }

  @PutMapping("/entries/{id}")
  PunishmentEntry replaceEntry(
    @RequestBody PunishmentEntry newEntry,
    @PathVariable String id
  ) {
    return queryEntry(id)
      .modify(UpdateOperators.set(newEntry))
      .execute();
  }

  @DeleteMapping("/entries/{id}")
  void deleteEntry(@PathVariable String id) {
    queryEntry(id).delete();
  }

  private Query<PunishmentEntry> queryEntries() {
    return datastore.find(PunishmentEntry.class);
  }

  private Query<PunishmentEntry> queryEntry(String id) {
    return queryEntries().filter(Filters.eq(FIELD_USER_ID, id));
  }
}
