package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginMemberService {

  private final MemberRepository memberRepository;

  private final PasswordEncoder passwordEncoder;

  public void login(String loginId, String password) {
    Member member = memberRepository.findByEmail(loginId)
        .orElseThrow(EntityNotFoundException::new);

    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new CustomAuthrizationException(ErrorCode.NOT_VALID_PASSWORD);
    }
  }
}
