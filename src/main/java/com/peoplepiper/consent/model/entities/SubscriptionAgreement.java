package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SubscriptionAgreement extends AbstractAgreement{
  private Boolean doubleOptIn;

  public SubscriptionAgreement() {
  }
}
