package com.peoplepiper.consent.configuration;

import com.peoplepiper.consent.configuration.properties.ConsentProperties;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@SuppressWarnings("unused")
public class SecurityConfiguration {
  @Bean
  CorsConfigurationSource corsConfigurationSource(ConsentProperties talentProperties){
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(talentProperties.getCors().getAllowedOrigins());
    configuration.setAllowedMethods(talentProperties.getCors().getAllowedMethods());
    configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
