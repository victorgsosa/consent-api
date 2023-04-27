package com.peoplepiper.consent.model.entities;

public interface Versionable<T extends Versionable<T>> extends Agreement {
  Long getId();
  AgreementVersion<T> current();

  AgreementVersion<T> newVersion(AgreementVersion<T> newVersion);
}
