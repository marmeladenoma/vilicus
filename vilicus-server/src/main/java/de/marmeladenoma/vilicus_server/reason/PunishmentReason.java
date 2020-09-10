package de.marmeladenoma.vilicus_server.reason;

import dev.morphia.annotations.*;

import java.util.List;

@Entity("reasons")
public final class PunishmentReason {
  @Id
  private String id;
  @Indexed(options = @IndexOptions(unique = true))
  private long reasonId;
  private String name;
  private List<PunishmentPhase> phases;

  public String getId() {
    return id;
  }

  public long getReasonId() {
    return reasonId;
  }

  public String getName() {
    return name;
  }

  public void setReasonId(long reasonId) {
    this.reasonId = reasonId;
  }

  public List<PunishmentPhase> getPhases() {
    return phases;
  }
}
