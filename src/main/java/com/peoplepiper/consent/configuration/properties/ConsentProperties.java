package com.peoplepiper.consent.configuration.properties;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "consent")
@Data
@NoArgsConstructor
public class ConsentProperties {
  private CorsProperties cors;

  @Data
  @NoArgsConstructor
  public static class CorsProperties {
    @Value("#{'${allowedOrigins}'.split(',)}")
    private List<String> allowedOrigins;

    @Value("#{'${allowedMethods}'.split(',)}")
    private List<String> allowedMethods;
  }
}
