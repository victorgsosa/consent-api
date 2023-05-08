package com.peoplepiper.consent.model.entities;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public interface Versionable<T extends AgreementVersion> extends Agreement {

  T current();

  T newVersion(T newVersion);
}
