package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.user.dto.request.UserCreateRequest;
import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService {

  private final UserRepository userRepository;

  public long create(UserCreateRequest request) {
    User user = new User(request.getLoginId(), request.getPassword(), request.getNickName());

    return userRepository.save(user).getId();
  }
}
