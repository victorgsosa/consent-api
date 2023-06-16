package com.peoplepiper.consent.model.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractUserVersionAgreement<T extends AbstractAgreementVersion<T>> extends AbstractUserAgreement{
  @ManyToOne
  private T agreementVersion;

  protected AbstractUserVersionAgreement() {
  }
}
