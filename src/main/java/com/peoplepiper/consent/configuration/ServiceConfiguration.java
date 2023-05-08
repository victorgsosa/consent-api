package com.peoplepiper.consent.configuration;

import com.peoplepiper.consent.services.AgreementServices;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public FactoryBean<?> agreementServiceFactoryFactoryBean(){
    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
    factoryBean.setServiceLocatorInterface(AgreementServices.AgreementServiceFactory.class);
    return factoryBean;
  }
}
