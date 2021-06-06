package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService {

  private final UserRepository userRepository;

  public void login(String loginId, String password) {
    User user = userRepository.findByLoginId(loginId)
        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
    validatePassword(password, user);
  }

  private void validatePassword(String password, User user) {
    if (!password.equals(user.getPassword())) {
      throw new IllegalStateException();
    }
  }
}
