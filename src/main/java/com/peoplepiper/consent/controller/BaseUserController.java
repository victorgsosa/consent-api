package com.peoplepiper.consent.controller;

import com.peoplepiper.consent.model.dto.UserAgreementAcceptance;
import com.peoplepiper.consent.model.entities.AbstractAgreement;
import com.peoplepiper.consent.model.entities.BaseUser;
import com.peoplepiper.consent.services.BaseUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class BaseUserController {
  private final BaseUserService baseUserService;

  public BaseUserController(BaseUserService baseUserService) {
    this.baseUserService = baseUserService;
  }

  @GetMapping
  public BaseUser findByID(
      @RequestParam String id
  ) {
    return getBaseUserService().findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exists")
        );
  }

  @PostMapping
  public BaseUser save(
      @RequestBody BaseUser user
  ){
    return getBaseUserService().save(user);
  }

  @PostMapping("agreements/accept")
  public BaseUser acceptAgreement(
      @RequestParam("id") String id,
      @RequestParam("agreementId") Long agreementId,
      @RequestBody UserAgreementAcceptance userAgreementAcceptance
  ) {
    return getBaseUserService().acceptAgreement(id, agreementId, userAgreementAcceptance);
  }

  @GetMapping("needed-agreements")
  public Iterable<? extends AbstractAgreement> findAgreementsToAccept(
      @RequestParam("id") String id
  ) {
    return getBaseUserService().findAgreementsToAccept(id);
  }

  public BaseUserService getBaseUserService() {
    return baseUserService;
  }
}
