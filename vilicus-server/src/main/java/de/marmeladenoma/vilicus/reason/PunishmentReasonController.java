package de.marmeladenoma.vilicus.reason;

import de.marmeladenoma.vilicus.entry.PunishmentEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PunishmentReasonController {
  @GetMapping("/reasons")
  List<PunishmentEntry> allReasons() {
    throw new UnsupportedOperationException();
  }

  @PostMapping("/reasons")
  PunishmentEntry newReason() {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/reasons/{id}")
  PunishmentEntry findReason(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }

  @PutMapping("/reasons/{id}")
  PunishmentEntry replaceReason(@PathVariable ObjectId id, @RequestBody PunishmentEntry newEntry) {
    throw new UnsupportedOperationException();
  }

  @DeleteMapping("/reasons/{id}")
  void deleteReason(@PathVariable ObjectId id) {
    throw new UnsupportedOperationException();
  }
}
