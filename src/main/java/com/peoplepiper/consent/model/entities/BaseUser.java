package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

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
  private List<AbstractUserAgreement> agreements;

  public BaseUser() {
    this.agreements = new ArrayList<>();
  }

  public void addAgreement(AbstractUserAgreement agreement) {
    this.getAgreements().add(agreement);
  }

}
