package de.marmeladenoma.vilicus_server.reason;

import dev.morphia.annotations.Embedded;

@Embedded
public final class PunishmentPhase {
  private byte type;
  private long duration;

  public byte getType() {
    return type;
  }

  public long getDuration() {
    return duration;
  }
}
