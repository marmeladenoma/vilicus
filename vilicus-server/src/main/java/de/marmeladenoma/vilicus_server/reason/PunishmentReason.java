package de.marmeladenoma.vilicus_server.reason;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;

@Entity("reasons")
public class PunishmentReason {
  @Id
  private String objectId;

  @Indexed(options = @IndexOptions(unique = true))
  private Long reasonId;

  private String name;

  public String getObjectId() {
    return objectId;
  }

  public Long getReasonId() {
    return reasonId;
  }

  public String getName() {
    return name;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
  }
}
