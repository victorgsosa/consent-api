package com.peoplepiper.consent.services;

import com.peoplepiper.consent.model.BaseUserRepository;
import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;
import com.peoplepiper.consent.model.entities.AbstractAgreement;
import com.peoplepiper.consent.model.entities.AbstractUserAgreement;
import com.peoplepiper.consent.model.entities.BaseUser;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl implements BaseUserService {

  private final BaseUserRepository baseUserRepository;
  private final AgreementServices.AgreementService agreementService;

  public BaseUserServiceImpl(BaseUserRepository baseUserRepository,
                             AgreementServices.AgreementService agreementService) {
    this.baseUserRepository = baseUserRepository;
    this.agreementService = agreementService;
  }

  @Override
  public Iterable<? extends AbstractAgreement> findAgreementsToAccept(String id) {
    return getAgreementService().findAgreementsToAccept(
        this.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User does not exists"))
    );
  }

  @Override
  public Optional<BaseUser> findById(String id) {
    return getBaseUserRepository().findById(id);
  }

  @Override
  public BaseUser save(BaseUser user) {
    return getBaseUserRepository().save(user);
  }

  @Override
  public BaseUser acceptAgreement(
      String id,
      Long agreementId,
      UserAgreementAcceptance userAgreementAcceptance
  ) {
    BaseUser user= this.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User does not exists"));
    AbstractUserAgreement userAgreement = this.getAgreementService().acceptForUser(
        agreementId,
        user,
        userAgreementAcceptance
    );
    user.addAgreement(userAgreement);
    return this.save(user);
  }

  public BaseUserRepository getBaseUserRepository() {
    return baseUserRepository;
  }

  public AgreementServices.AgreementService getAgreementService() {
    return agreementService;
  }
}
