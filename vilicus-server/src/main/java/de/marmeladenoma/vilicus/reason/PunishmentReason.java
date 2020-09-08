package de.marmeladenoma.vilicus.reason;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("reasons")
public class PunishmentReason {
  @Id
  private ObjectId id;

  private String name;

  public ObjectId getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
