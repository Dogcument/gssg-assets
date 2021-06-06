package com.gssg.gssgbe.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserCreateService userCreateService;
  private final UserLoginService userLoginService;

  public long create(String loginId, String password) {
    return userCreateService.create(loginId, password);
  }

  public void login(String loginId, String password) {
    userLoginService.login(loginId, password);
  }
}
