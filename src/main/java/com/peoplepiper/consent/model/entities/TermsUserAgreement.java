package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TermsUserAgreement extends AbstractUserVersionAgreement<TermsAgreementVersion>{
  public TermsUserAgreement() {
  }
}
