package de.marmeladenoma.vilicus_server.cache;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.marmeladenoma.susceptor.persistence.DatastoreFactory;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceConfig;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceModule;
import de.marmeladenoma.vilicus_server.counter.Counter;
import dev.morphia.Datastore;

public final class CacheInitialization {
  public static CacheInitialization create() {
    return new CacheInitialization();
  }

  private CacheInitialization() {}

  public Datastore initialize() {
    var config = createConfig(createConnectionString());
    var injector = Guice.createInjector(PersistenceModule.create(config));
    return loadData(injector);
  }

  private static final String DATABASE_NAME = "vilicus";
  private static final String COUNTER = "reason";

  private Datastore loadData(Injector injector) {
    var datastoreFactory = injector.getInstance(DatastoreFactory.class);
    var datastore = datastoreFactory.createDatastore(DATABASE_NAME);
    Counter.loadCounter(datastore, COUNTER);
    return datastore;
  }

  private PersistenceConfig createConfig(String connection) {
    return PersistenceConfig.newBuilder()
      .withMongoConnectionString(connection)
      .withMongoUser(USER)
      .withMongoPassword(PASSWORD)
      .withMongoSource(SOURCE)
      .build();
  }

  private static final String USER = System.getenv("MONGO_USER");
  private static final String PASSWORD = System.getenv("MONGO_PASSWORD");
  private static final String HOST = System.getenv("MONGO_HOST");
  private static final String PORT = System.getenv("MONGO_PORT");
  private static final String SOURCE = System.getenv("MONGO_SOURCE");
  private static final String DATABASE = System.getenv("MONGO_DB");
  private static final String CONNECTION_FORMAT
    = "mongodb://%s:%s@%s:%s/%s?authSource=%s";

  private String createConnectionString() {
    return String.format(CONNECTION_FORMAT,
      USER, PASSWORD, HOST, PORT, DATABASE, SOURCE);
  }
}
