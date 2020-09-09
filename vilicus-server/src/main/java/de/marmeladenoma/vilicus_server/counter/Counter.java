package de.marmeladenoma.vilicus_server.counter;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.springframework.stereotype.Service;

@Service
public final class Counter {

  private final Datastore datastore;

  public Counter(Datastore datastore) {
    this.datastore = datastore;
  }

  // Resolve an increment, like auto increment (sql)
  public long resolveIncrement(String counter) {
    CounterEntity counterEntity = queryCounter(datastore, counter)
      .modify(UpdateOperators.inc(CounterEntity.FIELD_SEQUENCE))
      .execute();

    return counterEntity.getSequence();
  }

  public static void loadCounter(Datastore datastore, String counter) {
    if (queryCounter(datastore, counter).count() > 0) {
      return;
    }
    datastore.save(new CounterEntity(counter));
  }

  private static Query<CounterEntity> queryCounter(
    Datastore datastore,
    String counter
  ) {
    return datastore.find(CounterEntity.class)
      .filter(Filters.eq(CounterEntity.FIELD_ID, counter));
  }
}
