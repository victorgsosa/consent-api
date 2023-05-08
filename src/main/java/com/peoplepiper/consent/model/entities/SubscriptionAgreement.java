package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class SubscriptionAgreement extends AbstractAgreement{
  private Boolean doubleOptIn;
  @Enumerated(EnumType.STRING)
  private SubscriptionChannels channel;

  public enum SubscriptionChannels {
    EMAIL, SMS
  }

  public SubscriptionAgreement() {
    this.setOptional(true);
  }
}
