package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class TermsAgreement extends AbstractVersionableAgreement<TermsAgreementVersion>{
  @Enumerated(EnumType.STRING)
  private TermsTypes type;

  public TermsAgreement() {
  }


  public enum TermsTypes {
    TERMS, POLICY
  }
}
