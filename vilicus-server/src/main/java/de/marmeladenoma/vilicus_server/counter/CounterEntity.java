package de.marmeladenoma.vilicus_server.counter;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("counter")
public final class CounterEntity {
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
