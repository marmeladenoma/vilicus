package de.marmeladenoma.vilicus.entity;

import de.marmeladenoma.vilicus.entry.PunishmentEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PunishmentPhaseController {
  @GetMapping("/phases")
  List<PunishmentEntry> allPhases() {
    throw new UnsupportedOperationException();
  }

  @PostMapping("/phases")
  PunishmentEntry newPhase() {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/phases/{id}")
  PunishmentEntry findPhase(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }

  @PutMapping("/phases/{id}")
  PunishmentEntry replacePhase(@PathVariable ObjectId id, @RequestBody PunishmentEntry newEntry) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/phases/{id}")
  void deletePhase(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
