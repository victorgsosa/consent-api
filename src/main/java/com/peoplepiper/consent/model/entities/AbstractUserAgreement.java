package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
@Table(name = "user_agreement_basic")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractUserAgreement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime acceptedAt;
  private String ip;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference("agreement")
  private AbstractAgreement agreement;

  @ManyToOne(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference("user")
  private BaseUser baseUser;

  protected AbstractUserAgreement() {
  }
}
