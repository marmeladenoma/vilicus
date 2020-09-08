package de.marmeladenoma.vilicus.counter;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;

// TODO: Move to susceptor-persistence?
/**
 * Util class for handling counter
 */
public class Counter {

  /**
   * Finds a sequence and increments it by one
   *
   * @return Next number in the sequence
   */
  public static Long next(Datastore datastore, String counter) {
    CounterEntity counterEntity = datastore.find(CounterEntity.class)
      .filter(Filters.eq("_id", counter))
      .modify(UpdateOperators.inc("seq"))
      .execute();

    return counterEntity.getSeq();
  }

  /**
   * Initializes a counter if it doesnt exist yet
   */
  public static void init(Datastore datastore, String counter) {
    if (datastore.find(CounterEntity.class)
      .filter(Filters.eq("_id", counter)).count() > 0)
      return;

    datastore.save(new CounterEntity(counter));
  }
}
