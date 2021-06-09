package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.user.dto.response.UserResponse;
import com.gssg.gssgbe.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserFindService {

  private final UserRepository userRepository;

  public List<UserResponse> findAll() {
    return userRepository.findAll().stream()
        .map(UserResponse::new)
        .collect(Collectors.toList());
  }
}
