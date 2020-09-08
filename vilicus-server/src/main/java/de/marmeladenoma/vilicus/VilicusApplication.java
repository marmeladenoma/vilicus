package de.marmeladenoma.vilicus;

import com.google.inject.Guice;
import de.marmeladenoma.susceptor.persistence.DatastoreFactory;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceConfig;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceModule;
import de.marmeladenoma.vilicus.counter.Counter;
import dev.morphia.Datastore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
  MongoAutoConfiguration.class,
  MongoDataAutoConfiguration.class
})
public class VilicusApplication {

  private static final String user = System.getenv("MONGO_USER");
  private static final String password = System.getenv("MONGO_PASSWORD");
  private static final String host = System.getenv("MONGO_HOST");
  private static final String port = System.getenv("MONGO_PORT");
  private static final String source = System.getenv("MONGO_SOURCE");
  private static final String db = System.getenv("MONGO_DB");

  public static Datastore datastore;

  public static void main(String[] args) {
    initDatastore();
    SpringApplication.run(VilicusApplication.class);
  }

  /**
   * Build connection to MongoDB using susceptor-persistence
   */
  private static void initDatastore() {
    var connectionString = "mongodb://" + user + ":" + password +
      "@" + host + ":" + port + "/" + db + "?authSource=" + source;
    var config = PersistenceConfig.newBuilder()
      .withMongoConnectionString(connectionString)
      .withMongoUser(user)
      .withMongoPassword(password)
      .withMongoSource(source)
      .build();
    var injector = Guice.createInjector(PersistenceModule.create(config));
    var datastoreFactory = injector.getInstance(DatastoreFactory.class);

    datastore = datastoreFactory.createDatastore("vilicus");

    Counter.init(datastore, "reason");
  }
}
