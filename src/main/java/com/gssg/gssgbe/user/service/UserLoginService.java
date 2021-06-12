package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
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

    if (!user.validPassword(password)) {
      throw new CustomAuthrizationException(ErrorCode.NOT_VALID_PASSWORD);
    }
  }
}
