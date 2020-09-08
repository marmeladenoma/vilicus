package de.marmeladenoma.vilicus.entry;

import de.marmeladenoma.vilicus.entity.PunishmentPhase;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.util.Date;

@Entity("entries")
public class PunishmentEntry {
  @Id
  private ObjectId id;

  private String playerUuid;

  private String playerIp;

  private Date creationDate;

  private String creatorUuid;

  @Reference
  private PunishmentPhase phase;

  public ObjectId getId() {
    return id;
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
