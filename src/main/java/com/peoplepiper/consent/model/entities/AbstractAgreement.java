package com.peoplepiper.consent.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;


@Entity
@Table(name = "agreement_basic")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class AbstractAgreement implements Agreement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String database;
  private String name;
  private String purpose;
  private LocalDateTime createdAt;
  private Boolean optional;

  protected AbstractAgreement() {
  }

  @Override
  public AbstractUserAgreement accept(BaseUser baseUser) {
    UserAgreement userAgreement = new UserAgreement();
    userAgreement.setBaseUser(baseUser);
    userAgreement.setAgreement(this);
    userAgreement.setAcceptedAt(LocalDateTime.now());
    return userAgreement;
  }

  @Override
  public boolean hasAgreement(BaseUser baseUser) {
    return baseUser.getAgreements().stream()
        .anyMatch(a -> Objects.equals(a.getAgreement() , this));
  }
}
