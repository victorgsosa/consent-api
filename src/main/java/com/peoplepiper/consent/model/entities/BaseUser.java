package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class BaseUser {
  @Id
  private String id;
  private String database;

  @OneToMany(
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      mappedBy = "baseUser"
  )
  @JsonManagedReference("user")
  private Set<AbstractUserAgreement> agreements;

  public BaseUser() {
    this.agreements = new HashSet<>();
  }

  public void addAgreement(AbstractUserAgreement agreement) {
    this.getAgreements().add(agreement);
  }

}
