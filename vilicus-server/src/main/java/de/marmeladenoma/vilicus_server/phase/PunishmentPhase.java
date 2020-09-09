package de.marmeladenoma.vilicus_server.phase;

import de.marmeladenoma.vilicus_server.reason.PunishmentReason;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

@Entity("phases")
public final class PunishmentPhase {
  public static final String FIELD_ID = "_id";
  public static final String FIELD_TYPE = "type";
  public static final String FIELD_DURATION = "duration";
  public static final String FIELD_REASON = "reason";

  @Id
  private String id;
  private byte type;
  private long duration;
  @Reference
  private PunishmentReason reason;

  public String getId() {
    return id;
  }

  public byte getType() {
    return type;
  }

  public long getDuration() {
    return duration;
  }

  public PunishmentReason getReason() {
    return reason;
  }
}
