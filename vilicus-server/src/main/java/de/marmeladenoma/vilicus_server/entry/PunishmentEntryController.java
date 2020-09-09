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

  public PunishmentEntryController(Datastore datastore) {
    this.datastore = datastore;
  }

  // Mappings

  @GetMapping("/entries")
  Collection<PunishmentEntry> allEntries(@RequestParam Optional<String> userId) {
    Query<PunishmentEntry> query = datastore.find(PunishmentEntry.class);

    if (userId.isPresent())
      query = query.filter(
        Filters.eq(PunishmentEntry.FIELD_USER_ID, userId.get())
      );

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

  // Queries

  private Query<PunishmentEntry> queryEntries() {
    return datastore.find(PunishmentEntry.class);
  }

  private Query<PunishmentEntry> queryEntry(String id) {
    return queryEntries().filter(Filters.eq(PunishmentEntry.FIELD_ID, id));
  }
}
