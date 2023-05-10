package com.peoplepiper.consent.services;

import com.peoplepiper.consent.model.AgreementRepositories;
import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;
import com.peoplepiper.consent.model.entities.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

public class AgreementServices {
  public final static String SUBSCRIPTION = "com.peoplepiper.consent.model.entities" +
      ".SubscriptionAgreement";
  public final static String TERMS = "com.peoplepiper.consent.model.entities.TermsAgreement";
  public final static String AGREEMENT = "com.peoplepiper.consent.model.entities.AbstractAgreement";


  public static abstract class AbstractAgreementService<T extends AbstractAgreement> {
    private final AgreementRepositories.BaseAgreementRepository<T> agreementRepository;

    protected AbstractAgreementService(AgreementRepositories.BaseAgreementRepository<T> agreementRepository) {
      this.agreementRepository = agreementRepository;
    }

    public T save(T agreement) {
      agreement.setCreatedAt(LocalDateTime.now());
      return getAgreementRepository().save(agreement);
    }

    public Optional<T> findById(Long id) {
      return getAgreementRepository().findById(id);
    }

    public AbstractUserAgreement acceptForUser(Long id, BaseUser user, UserAgreementAcceptance userAgreementAcceptance) {
      Assert.notNull(id, "Id cannot be null");
      Assert.notNull(user, "User cannot be null");
      T agreement = this.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("agreement does not exists"));
      return agreement.accept(user, userAgreementAcceptance);
    }
    public Iterable<? extends AbstractAgreement> findAgreementsToAccept(BaseUser user) {
      Assert.notNull(user, "User cannot be null");
      return StreamSupport.stream(
              this.getAgreementRepository().findByDatabase(user.getDatabase()).spliterator(),
              true
          ).filter(a -> !a.hasAgreement(user))
          .collect(Collectors.toList());
    }


    public AgreementRepositories.BaseAgreementRepository<T> getAgreementRepository() {
      return agreementRepository;
    }
  }

  @Service(AgreementServices.AGREEMENT)
  public static class AgreementService extends AbstractAgreementService<AbstractAgreement> {

    protected AgreementService(AgreementRepositories.AgreementRepository agreementRepository) {
      super(agreementRepository);
    }
  }

  @Service(AgreementServices.SUBSCRIPTION)
  public static class SubscriptionAgreementService extends AbstractAgreementService<SubscriptionAgreement> {

    protected SubscriptionAgreementService(AgreementRepositories.SubscriptionAgreementRepository agreementRepository) {
      super(agreementRepository);
    }
  }

  @Service(AgreementServices.TERMS)
  public static class TermsAgreementService extends AbstractAgreementService<TermsAgreement> {

    protected TermsAgreementService(AgreementRepositories.TermsAgreementRepository agreementRepository) {
      super(agreementRepository);
    }

    @Override
    public TermsAgreement save(TermsAgreement agreement) {
      agreement.getVersions().stream().forEach(v -> {
        if (v.getCreatedAt() == null) {
          v.setCreatedAt(LocalDateTime.now());
        }
      });
      return super.save(agreement);
    }
  }

  public interface AgreementServiceFactory {
    AbstractAgreementService<?> getService(String id);
  }
}
