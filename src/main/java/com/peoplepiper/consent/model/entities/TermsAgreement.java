package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TermsAgreement extends AbstractVersionableAgreement<TermsAgreementVersion>{
  @Enumerated(EnumType.STRING)
  private TermsTypes type;

  public TermsAgreement() {
  }


  public enum TermsTypes {
    TERMS, POLICY
  }
}
