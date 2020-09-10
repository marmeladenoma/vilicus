package de.marmeladenoma.vilicus_server.entry;

import de.marmeladenoma.vilicus_server.reason.PunishmentReason;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

import java.util.Date;

@Entity("entries")
public final class PunishmentEntry {
  @Id
  private String id;
  private String userId;
  private String ip;
  private Date creationDate;
  private String creatorId;
  @Reference
  private PunishmentReason reason;
  private int phaseIndex;
  private boolean active;

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getIp() {
    return ip;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public PunishmentReason getReason() {
    return reason;
  }

  public int getPhaseIndex() {
    return phaseIndex;
  }

  public boolean isActive() {
    return active;
  }
}
