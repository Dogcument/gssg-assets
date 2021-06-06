package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService {

  private final UserRepository userRepository;

  public long create(String loginId, String password) throws RuntimeException {
    User user = new User(loginId, password);

    return userRepository.save(user).getId();
  }
}
