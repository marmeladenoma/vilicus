package de.marmeladenoma.vilicus_server.entry;

import de.marmeladenoma.vilicus_server.phase.PunishmentPhase;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;

import java.util.Date;

@Entity("entries")
public final class PunishmentEntry {
  public static final String FIELD_ID = "_id";
  public static final String FIELD_USER_ID = "userId";
  public static final String FIELD_IP = "ip";
  public static final String FIELD_CREATION_DATE = "creationDate";
  public static final String FIELD_CREATOR_ID = "creatorId";
  public static final String FIELD_PHASE = "phase";
  public static final String FIELD_DISABLED = "disabled";

  @Id
  private String id;
  private String userId;
  private String ip;
  private Date creationDate;
  private String creatorId;
  @Reference
  private PunishmentPhase phase;
  private boolean disabled;

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

  public boolean isDisabled() {
    return disabled;
  }
}
