package de.marmeladenoma.vilicus.phase;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PunishmentPhaseController {
  @GetMapping("/phases")
  List<PunishmentPhase> allPhases() {
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
  PunishmentPhase replacePhase(@PathVariable ObjectId id, @RequestBody PunishmentPhase newEntry) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/phases/{id}")
  void deletePhase(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
