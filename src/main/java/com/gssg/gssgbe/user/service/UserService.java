package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.controller.user.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

  private final UserCreateService userCreateService;
  private final UserLoginService userLoginService;

  public long create(UserCreateRequest request) {
    return userCreateService.create(request);
  }

  public void login(String loginId, String password) {
    userLoginService.login(loginId, password);
  }
}
