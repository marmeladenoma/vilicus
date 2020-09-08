package de.marmeladenoma.vilicus.phase;

import de.marmeladenoma.vilicus.PunishmentType;
import de.marmeladenoma.vilicus.reason.PunishmentReason;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

@Entity("phases")
public class PunishmentPhase {
  @Id
  private String objectId;

  private Integer typeOrdinal;

  private Integer duration;

  @Reference
  private PunishmentReason reason;

  public String getObjectId() {
    return objectId;
  }

  public PunishmentType getType() {
    return PunishmentType.values()[typeOrdinal];
  }

  public Integer getDuration() {
    return duration;
  }

  public PunishmentReason getReason() {
    return reason;
  }
}
