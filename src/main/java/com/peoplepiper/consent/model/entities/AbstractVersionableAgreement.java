package com.peoplepiper.consent.model.entities;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public abstract class AbstractVersionableAgreement<T extends AbstractVersionableAgreement<T>>
    extends AbstractAgreement implements Versionable<T>{
  private List<AgreementVersion<T>> versions;
  private Long currentVersion;

  @Override
  public AbstractUserAgreement accept(User user) {
    return current().accept(user);
  }

  @Override
  public AgreementVersion<T> current() {
    return getVersions().stream()
        .filter((v) -> v.getVersion().equals(currentVersion))
        .findFirst().orElse(null);
  }

  @Override
  public AgreementVersion<T> newVersion(AgreementVersion<T> newVersion) {
    LocalDateTime now = LocalDateTime.now();
    newVersion.setSince(now);
    newVersion.setUntil(LocalDateTime.MAX);
    AgreementVersion<T> current = this.current();
    current.setUntil(now);
    this.setCurrentVersion(newVersion.getVersion());
    this.getVersions().add(newVersion);
    return newVersion;
  }
}
