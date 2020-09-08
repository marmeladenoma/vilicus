package de.marmeladenoma.vilicus;

import com.google.inject.Guice;
import de.marmeladenoma.susceptor.persistence.DatastoreFactory;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceConfig;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceModule;
import dev.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaAutoConfiguration {

  // TODO: Clean up

  private static final String user = System.getenv("MONGO_USER");
  private static final String password = System.getenv("MONGO_PASSWORD");
  private static final String host = System.getenv("MONGO_HOST");
  private static final String port = System.getenv("MONGO_PORT");
  private static final String source = System.getenv("MONGO_SOURCE");

  @Bean
  public Datastore datastore() {
    var connectionString = "mongodb://" + user + ":" + password +
      "@" + host + ":" + port + "/" + source;
    var config = PersistenceConfig.newBuilder()
      .withMongoConnectionString(connectionString)
      .withMongoUser(user)
      .withMongoPassword(password)
      .withMongoSource(source)
      .build();
    var injector = Guice.createInjector(PersistenceModule.create(config));
    var datastoreFactory = injector.getInstance(DatastoreFactory.class);

    return datastoreFactory.createDatastore("vilicus");
  }
}
