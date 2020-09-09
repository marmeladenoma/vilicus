package de.marmeladenoma.vilicus_server;

import de.marmeladenoma.vilicus_server.cache.CacheInitialization;
import dev.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaAutoConfiguration {

  private final Datastore datastore;

  public MorphiaAutoConfiguration() {
    var cacheInitialization = CacheInitialization.create();
    cacheInitialization.initialize();
    this.datastore = cacheInitialization.getDatastore();
  }

  @Bean
  public Datastore datastore() {
    return datastore;
  }
}
