package de.marmeladenoma.vilicus_server.entry;

import de.marmeladenoma.vilicus_server.phase.PunishmentPhase;
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
  private PunishmentPhase phase;
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

  public PunishmentPhase getPhase() {
    return phase;
  }

  public boolean isActive() {
    return active;
  }
}
