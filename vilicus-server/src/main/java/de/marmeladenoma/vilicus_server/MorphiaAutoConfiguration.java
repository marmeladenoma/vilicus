package de.marmeladenoma.vilicus_server;

import de.marmeladenoma.vilicus_server.cache.CacheInitialization;
import dev.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaAutoConfiguration {
  private final Datastore datastore;

  // Because of the @Configuration annotation
  // this constructor needs to be public
  public MorphiaAutoConfiguration() {
    this.datastore = createDatastore();
  }

  private Datastore createDatastore() {
    var cacheInitialization = CacheInitialization.create();
    return cacheInitialization.initialize();
  }

  @Bean
  public Datastore datastore() {
    return datastore;
  }
}
