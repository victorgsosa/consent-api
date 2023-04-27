package com.peoplepiper.consent.model.entities;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TermsAgreementVersion extends AbstractAgreementVersion<TermsAgreement>{
  private String Url;

  public TermsAgreementVersion() {
  }

  public TermsAgreementVersion(TermsAgreement agreement) {
    super(agreement);
  }

  public TermsAgreementVersion(TermsAgreement agreement, Long version) {
    super(agreement, version);
  }


}
