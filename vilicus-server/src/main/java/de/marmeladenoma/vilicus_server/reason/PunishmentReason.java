package de.marmeladenoma.vilicus_server.reason;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;

@Entity("reasons")
public final class PunishmentReason {
  @Id
  private String id;
  @Indexed(options = @IndexOptions(unique = true))
  private long reasonId;
  private String name;

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
}
