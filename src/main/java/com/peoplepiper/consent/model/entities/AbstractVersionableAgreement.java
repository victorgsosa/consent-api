package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractVersionableAgreement<T extends AbstractAgreementVersion<T>>
    extends AbstractAgreement implements Versionable<T> {
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      mappedBy = "agreement"
  )
  @JsonManagedReference
  private Set<T> versions;
  private Long currentVersion = 0l;

  protected AbstractVersionableAgreement() {
    this.versions = new HashSet<>();
  }


  @Override
  public AbstractUserAgreement accept(BaseUser baseUser, UserAgreementAcceptance userAgreementAcceptance) {
    return current().accept(baseUser, userAgreementAcceptance);
  }

  @Override
  public boolean hasAgreement(BaseUser baseUser) {
    AbstractUserAgreement agreement = baseUser.getAgreements().stream()
        .filter(a -> a.getAgreement().equals(this))
        .findFirst()
        .orElse(null);
    if (!(agreement instanceof AbstractUserVersionAgreement<?>))
      return false;
    return Objects.equals(
        ((AbstractUserVersionAgreement<?>) agreement).getAgreementVersion(),
        this.current()
    );

  }

  @Override
  public T current() {
    return getVersions().stream()
        .filter((v) -> v.getVersion().equals(currentVersion))
        .findFirst().orElse(null);
  }

  @Override
  public T newVersion(T newVersion) {
    LocalDateTime now = LocalDateTime.now();
    newVersion.setSince(now);
    newVersion.setUntil(LocalDateTime.MAX);
    T current = this.current();
    current.setUntil(now);
    newVersion.setAgreement(this);
    newVersion.setVersion(current.getVersion() + 1);
    this.setCurrentVersion(newVersion.getVersion());
    this.getVersions().add(newVersion);
    return newVersion;
  }

  public void setVersions(Set<T> versions) {
    this.versions.clear();
    this.versions.addAll(versions);
  }
}
