package com.peoplepiper.consent.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class JacksonConfiguration {
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> builder
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .failOnUnknownProperties(false)
        .modulesToInstall(
            new Hibernate5Module()
                .disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION)
        );
  }
}
