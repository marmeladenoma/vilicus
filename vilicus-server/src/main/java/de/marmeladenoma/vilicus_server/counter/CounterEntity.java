package de.marmeladenoma.vilicus_server.counter;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("counter")
public final class CounterEntity {
  // This framework requires public constructors
  public CounterEntity() {}

  private static final long DEFAULT_SEQUENCE = 0;

  public CounterEntity(String counter) {
    this.counter = counter;
    this.sequence = DEFAULT_SEQUENCE;
  }

  @Id
  private String counter;
  private long sequence;

  public long getSequence() {
    return sequence;
  }
}
