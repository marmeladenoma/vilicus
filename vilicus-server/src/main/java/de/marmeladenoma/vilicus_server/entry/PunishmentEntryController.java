package de.marmeladenoma.vilicus_server.entry;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public final class PunishmentEntryController {
  @GetMapping("/entries")
  Collection<PunishmentEntry> allEntries() {
    throw new UnsupportedOperationException();
  }

  @PostMapping("/entries")
  PunishmentEntry newEntry() {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/entries/{id}")
  PunishmentEntry findEntry(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }

  @PutMapping("/entries/{id}")
  PunishmentEntry replaceEntry(
    @RequestBody PunishmentEntry newEntry,
    @PathVariable ObjectId id
  ) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/entries/{id}")
  void deleteEntry(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
