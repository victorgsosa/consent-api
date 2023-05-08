package com.peoplepiper.consent.model.entities;

import javax.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractUserVersionAgreement<T extends AbstractAgreementVersion<T>> extends AbstractUserAgreement{
  @ManyToOne
  private T agreementVersion;

  protected AbstractUserVersionAgreement() {
  }
}
