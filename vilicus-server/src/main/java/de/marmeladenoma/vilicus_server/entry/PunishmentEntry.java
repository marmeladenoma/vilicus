package de.marmeladenoma.vilicus_server.entry;

import de.marmeladenoma.vilicus_server.phase.PunishmentPhase;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

import java.util.Date;

@Entity("entries")
public class PunishmentEntry {
  @Id
  private String objectId;

  private String playerUuid;

  private String playerIp;

  private Date creationDate;

  private String creatorUuid;

  @Reference
  private PunishmentPhase phase;

  public String getObjectId() {
    return objectId;
  }

  public String getPlayerUuid() {
    return playerUuid;
  }

  public String getPlayerIp() {
    return playerIp;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public String getCreatorUuid() {
    return creatorUuid;
  }

  public PunishmentPhase getPhase() {
    return phase;
  }
}
