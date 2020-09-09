package de.marmeladenoma.vilicus_server.phase;

import de.marmeladenoma.vilicus_server.reason.PunishmentReason;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

@Entity("phases")
public final class PunishmentPhase {
  @Id
  private String id;
  private int typeOrdinal;
  private long duration;
  @Reference
  private PunishmentReason reason;

  public String getId() {
    return id;
  }

  public int getTypeOrdinal() {
    return typeOrdinal;
  }

  public long getDuration() {
    return duration;
  }

  public PunishmentReason getReason() {
    return reason;
  }
}
