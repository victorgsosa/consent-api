package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class TermsAgreementVersion extends AbstractAgreementVersion<TermsAgreementVersion>{
  private String url;

  @MapsId("agreementId")
  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  protected TermsAgreement agreement;

  public TermsAgreementVersion() {
    super();
    this.agreement = new TermsAgreement();
  }

  public TermsAgreementVersion(TermsAgreement agreement) {
    super(agreement);
    this.agreement = new TermsAgreement();
  }

  public TermsAgreementVersion(TermsAgreement agreement, Long version) {
    super(agreement, version);
    this.agreement = new TermsAgreement();
  }

  @Override
  public TermsUserAgreement accept(BaseUser baseUser) {
    TermsUserAgreement termsUserAgreement = new TermsUserAgreement();
    termsUserAgreement.setBaseUser(baseUser);
    termsUserAgreement.setAgreement(this.getAgreement());
    termsUserAgreement.setAgreementVersion(this);
    termsUserAgreement.setAcceptedAt(LocalDateTime.now());
    return termsUserAgreement;
  }
}
