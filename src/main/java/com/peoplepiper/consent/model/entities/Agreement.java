package com.peoplepiper.consent.model.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "agreementType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(name = Agreement.SUBSCRIPTION_TYPE, value = SubscriptionAgreement.class),
    @JsonSubTypes.Type(name = Agreement.TERMS_TYPE, value = TermsAgreement.class),
})
public interface Agreement {
  String SUBSCRIPTION_TYPE = "subscription";
  String TERMS_TYPE = "terms";
  AbstractUserAgreement accept(BaseUser baseUser);

  boolean hasAgreement(BaseUser baseUser);
}
