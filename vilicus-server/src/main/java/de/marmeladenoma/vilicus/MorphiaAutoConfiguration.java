package de.marmeladenoma.vilicus;

import dev.morphia.Datastore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaAutoConfiguration {

  @Bean
  public Datastore datastore() {
    return VilicusApplication.datastore;
  }
}
