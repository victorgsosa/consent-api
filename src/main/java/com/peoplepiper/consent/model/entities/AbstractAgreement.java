package com.peoplepiper.consent.model.entities;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;


@Entity
@Table(name = "agreement")
@Data
public abstract class AbstractAgreement implements Agreement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String purpose;
  private LocalDateTime createdAt;

  @Override
  public UserAgreement accept(User user) {
    AbstractUserAgreement userAgreement = new UserAgreement(user, this);
    return userAgreement;
  }
}
