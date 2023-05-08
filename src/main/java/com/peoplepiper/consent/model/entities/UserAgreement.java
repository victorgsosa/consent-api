package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class UserAgreement extends AbstractUserAgreement{
  @ManyToOne(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference
  private AbstractAgreement agreement;

  public UserAgreement() {
  }
}
