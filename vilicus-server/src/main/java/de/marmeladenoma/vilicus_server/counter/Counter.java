package de.marmeladenoma.vilicus_server.counter;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;

public final class Counter {
  private Counter() {}

  private static final String FILTER = "_id";
  private static final String SEQUENCE_NAME = "seq";

  // Resolve an increment, like auto increment (sql)
  public static long resolveIncrement(Datastore datastore, String counter) {
    CounterEntity counterEntity = datastore.find(CounterEntity.class)
      .filter(Filters.eq(FILTER, counter))
      .modify(UpdateOperators.inc(SEQUENCE_NAME))
      .execute();

    return counterEntity.getSequence();
  }

  public static void loadCounter(Datastore datastore, String counter) {
    if (resolveEntities(datastore, counter) > 0) {
      return;
    }
    datastore.save(new CounterEntity(counter));
  }

  private static long resolveEntities(Datastore datastore, String counter) {
    return datastore.find(CounterEntity.class)
      .filter(Filters.eq(FILTER, counter))
      .count();
  }
}
