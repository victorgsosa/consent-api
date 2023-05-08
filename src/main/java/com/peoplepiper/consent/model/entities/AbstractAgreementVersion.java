package com.peoplepiper.consent.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractAgreementVersion<T extends AbstractAgreementVersion<T>>
    implements AgreementVersion{
  @EmbeddedId
  private AgreementVersionKey key;


  private LocalDateTime createdAt;
  private LocalDateTime since;
  private LocalDateTime until;

  protected AbstractAgreementVersion() {
    this.key = new AgreementVersionKey();
  }

  protected AbstractAgreementVersion(AbstractVersionableAgreement<T> agreement) {
    this.setAgreement(agreement);
  }

  protected AbstractAgreementVersion(AbstractVersionableAgreement<T> agreement, Long version) {
    this(agreement);
    this.setVersion(version);
  }

  public Long getVersion() {
    return this.getKey().getVersion();
  }

  public void setVersion(Long version){
    this.getKey().setVersion(version);
  }

  public void setAgreement(AbstractVersionableAgreement<T> agreement) {
    this.getKey().setAgreementId(agreement.getId());
  }


  @Embeddable
  @Data
  public static class AgreementVersionKey implements Serializable {
    private Long agreementId;
    private Long version;

    public AgreementVersionKey() {
    }
  }
}
