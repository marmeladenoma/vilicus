package de.marmeladenoma.vilicus_server.entry;

import com.mongodb.client.result.DeleteResult;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  private static final String FIELD_CREATOR_ID = "creatorId";
  private static final String FIELD_ACTIVE_ID = "active";

  @GetMapping("/entries")
  Collection<PunishmentEntry> allEntries(
    @RequestParam Optional<String> userId,
    @RequestParam Optional<String> creatorId,
    @RequestParam Optional<Boolean> active
  ) {
    Query<PunishmentEntry> query = queryEntries();
    if (userId.isPresent()) {
      query = query.filter(Filters.eq(FIELD_USER_ID, userId.get()));
    }
    if(creatorId.isPresent()) {
      query = query.filter(Filters.eq(FIELD_CREATOR_ID, creatorId.get()));
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
  ResponseEntity<PunishmentEntry> findEntry(@PathVariable String id) {
    var query = queryEntry(id);
    if (query.count() == 0)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(query.first(), HttpStatus.OK);
  }

  @PutMapping("/entries/{id}")
  ResponseEntity<PunishmentEntry> replaceEntry(
    @RequestBody PunishmentEntry newEntry,
    @PathVariable String id
  ) {
    var query = queryEntry(id);
    if (query.count() == 0)
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    var entry = query
      .modify(UpdateOperators.set(newEntry))
      .execute();
    return new ResponseEntity<>(entry, HttpStatus.OK);
  }

  @DeleteMapping("/entries/{id}")
  ResponseEntity<Void> deleteEntry(@PathVariable String id) {
    var deleteResult = queryEntry(id).delete();
    return deleteResult.getDeletedCount() > 0
      ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  private Query<PunishmentEntry> queryEntries() {
    return datastore.find(PunishmentEntry.class);
  }

  private static final String FIELD_ID = "_id";

  private Query<PunishmentEntry> queryEntry(String id) {
    return queryEntries().filter(Filters.eq(FIELD_ID, id));
  }
}
