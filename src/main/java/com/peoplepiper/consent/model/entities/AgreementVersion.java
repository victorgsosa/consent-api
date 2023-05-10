package com.peoplepiper.consent.model.entities;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "agreementVersionType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(name = AgreementVersion.TERMS_TYPE, value = TermsAgreementVersion.class),
})
public interface AgreementVersion {
  String TERMS_TYPE = "terms";
  Long getVersion();

  Agreement getAgreement();
  AbstractUserVersionAgreement accept(BaseUser baseUser, UserAgreementAcceptance userAgreementAcceptance);
}
