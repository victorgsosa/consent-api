package com.peoplepiper.consent.model;

import com.peoplepiper.consent.model.entities.BaseUser;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface BaseUserRepository extends Repository<BaseUser, String> {
  BaseUser save(BaseUser baseUser);
  Optional<BaseUser> findById(String id);
}
