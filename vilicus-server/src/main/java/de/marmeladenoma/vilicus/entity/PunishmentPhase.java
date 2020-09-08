package de.marmeladenoma.vilicus.entity;

import de.marmeladenoma.vilicus.PunishmentType;
import de.marmeladenoma.vilicus.reason.PunishmentReason;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity("phases")
public class PunishmentPhase {
  @Id
  private ObjectId id;

  private Integer typeOrdinal;

  private Integer duration;

  @Reference
  private PunishmentReason reason;

  public ObjectId getId() {
    return id;
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
