package com.peoplepiper.consent.model.entities;

import java.time.LocalDateTime;
import javax.persistence.MapsId;
import lombok.Data;


@Data
public abstract class AbstractUserAgreement {
  private LocalDateTime acceptedAt;
  private String ip;
}
