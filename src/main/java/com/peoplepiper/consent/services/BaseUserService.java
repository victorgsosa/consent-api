package com.peoplepiper.consent.services;

import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;
import com.peoplepiper.consent.model.entities.AbstractAgreement;
import com.peoplepiper.consent.model.entities.BaseUser;
import java.util.Optional;

public interface BaseUserService {
  Iterable<? extends AbstractAgreement> findAgreementsToAccept(String id);

  Optional<BaseUser> findById(String id);

  BaseUser save(BaseUser user);

  BaseUser acceptAgreement(
      String id,
      Long agreementId,
      UserAgreementAcceptance userAgreementAcceptance
  );
}
