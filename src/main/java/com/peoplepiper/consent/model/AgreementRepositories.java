package com.peoplepiper.consent.model;

import com.peoplepiper.consent.model.entities.AbstractAgreement;
import com.peoplepiper.consent.model.entities.SubscriptionAgreement;
import com.peoplepiper.consent.model.entities.TermsAgreement;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

public interface AgreementRepositories {
  @NoRepositoryBean
  interface BaseAgreementRepository<T extends AbstractAgreement> extends Repository<T, Long> {
    T save(T agreement);
    Iterable<T> findAll();
    Iterable<T> findByDatabase(String database);
    Optional<T> findById(Long id);
  }

  interface AgreementRepository extends BaseAgreementRepository<AbstractAgreement> {

  }

  interface SubscriptionAgreementRepository extends BaseAgreementRepository<SubscriptionAgreement> {

  }

  interface TermsAgreementRepository extends BaseAgreementRepository<TermsAgreement> {

  }
}
