package de.marmeladenoma.vilicus_server.phase;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public final class PunishmentPhaseController {
  @GetMapping("/phases")
  Collection<PunishmentPhase> allPhases() {
    throw new UnsupportedOperationException();
  }

  @PostMapping("/phases")
  PunishmentPhase newPhase() {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/phases/{id}")
  PunishmentPhase findPhase(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }

  @PutMapping("/phases/{id}")
  PunishmentPhase replacePhase(
    @RequestBody PunishmentPhase newEntry,
    @PathVariable ObjectId id
  ) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/phases/{id}")
  void deletePhase(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
