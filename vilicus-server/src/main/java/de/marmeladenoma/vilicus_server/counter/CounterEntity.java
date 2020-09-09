package de.marmeladenoma.vilicus_server.counter;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("counter")
public final class CounterEntity {
  public static final String FIELD_ID = "_id";
  public static final String FIELD_SEQUENCE = "sequence";

  // This framework requires public constructors
  public CounterEntity() {}

  public CounterEntity(String counter) {
    this.counter = counter;
    this.sequence = 0L;
  }

  @Id
  private String counter;
  private long sequence;

  public long getSequence() {
    return sequence;
  }
}
