package com.peoplepiper.consent.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class AbstractAgreementVersion<T extends Versionable<T>>
    extends AbstractAgreement implements AgreementVersion<T>{
  @EmbeddedId
  private AgreementVersionKey key;

  @MapsId("agreementId")
  private T agreement;

  private LocalDateTime createdAt;
  private LocalDateTime since;
  private LocalDateTime until;

  public AbstractAgreementVersion() {
    this.key = new AgreementVersionKey();
  }

  public AbstractAgreementVersion(T agreement) {
    this.agreement = agreement;
    this.key.setAgreementId(agreement.getId());
  }

  protected AbstractAgreementVersion(T agreement, Long version) {
    this(agreement);
    this.key.setVersion(version);
  }

  public Long getVersion() {
    return this.getKey().getVersion();
  }

  public void setAgreement(T agreement) {
    this.getKey().setAgreementId(agreement.getId());
    this.agreement = agreement;
  }

  @Embeddable
  @Data
  public static class AgreementVersionKey implements Serializable {
    private Long agreementId;
    private Long version;
  }

}
