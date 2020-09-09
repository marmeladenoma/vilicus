package de.marmeladenoma.vilicus_server;

import de.marmeladenoma.vilicus_server.cache.CacheInitialization;
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
  public static Datastore datastore;

  public static void main(String[] args) {
    CacheInitialization.create().initialize();
    SpringApplication.run(VilicusApplication.class);
  }
}
