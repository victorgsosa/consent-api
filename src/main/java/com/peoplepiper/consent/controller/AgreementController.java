package com.peoplepiper.consent.controller;

import com.peoplepiper.consent.model.entities.AbstractAgreement;
import com.peoplepiper.consent.services.AgreementServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agreement")
public class AgreementController {
  private final AgreementServices.AgreementServiceFactory serviceFactory;

  public AgreementController(AgreementServices.AgreementServiceFactory serviceFactory) {
    this.serviceFactory = serviceFactory;
  }

  @PostMapping
  public AbstractAgreement save(@RequestBody AbstractAgreement agreement){
    return getService(agreement).save(agreement);
  }

  @SuppressWarnings("unchecked")
  public <T extends AbstractAgreement> AgreementServices.AbstractAgreementService<T> getService(T agreement){
    return (AgreementServices.AbstractAgreementService<T>) getServiceFactory()
        .getService(agreement.getClass().getName());
  }

  public AgreementServices.AgreementServiceFactory getServiceFactory() {
    return serviceFactory;
  }
}
