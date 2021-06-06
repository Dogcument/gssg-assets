package com.gssg.gssgbe.user.service;

import com.gssg.gssgbe.user.entity.User;
import com.gssg.gssgbe.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserFindService {

  private final UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
