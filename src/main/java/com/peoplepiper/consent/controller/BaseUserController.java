package com.peoplepiper.consent.controller;

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

  @GetMapping("{id}")
  public BaseUser findByID(
      @PathVariable String id
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

  @PostMapping("{id}/agreements/{agreementId}/accept")
  public BaseUser acceptAgreement(
      @PathVariable("id") String id,
      @PathVariable("agreementId") Long agreementId
  ) {
    return getBaseUserService().acceptAgreement(id, agreementId);
  }

  @GetMapping("{id}/needed-agreements")
  public Iterable<? extends AbstractAgreement> findAgreementsToAccept(
      @PathVariable("id") String id
  ) {
    return getBaseUserService().findAgreementsToAccept(id);
  }

  public BaseUserService getBaseUserService() {
    return baseUserService;
  }
}
