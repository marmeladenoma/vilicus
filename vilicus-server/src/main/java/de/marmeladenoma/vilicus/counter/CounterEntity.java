package de.marmeladenoma.vilicus.counter;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("counter")
public class CounterEntity {

  public CounterEntity() {}

  public CounterEntity(String counter) {
    this.counter = counter;
    this.seq = 0L;
  }

  @Id
  private String counter;

  private Long seq;

  public Long getSeq() {
    return seq;
  }
}
