package com.peoplepiper.consent.model.entities;

import java.time.LocalDateTime;

public interface AgreementVersion<T extends Versionable<T>> extends Agreement {
  Long getVersion();
  void setSince(LocalDateTime since);
  void setUntil(LocalDateTime until);
}
