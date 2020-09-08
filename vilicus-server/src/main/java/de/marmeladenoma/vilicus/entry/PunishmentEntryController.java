package de.marmeladenoma.vilicus.entry;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PunishmentEntryController {
  @GetMapping("/entries")
  List<PunishmentEntry> allEntries() {
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
  PunishmentEntry replaceEntry(@PathVariable ObjectId id, @RequestBody PunishmentEntry newEntry) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/entries/{id}")
  void deleteEntry(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
