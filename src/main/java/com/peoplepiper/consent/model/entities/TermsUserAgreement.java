package com.peoplepiper.consent.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TermsUserAgreement extends AbstractUserVersionAgreement<TermsAgreementVersion>{
  public TermsUserAgreement() {
  }
}
