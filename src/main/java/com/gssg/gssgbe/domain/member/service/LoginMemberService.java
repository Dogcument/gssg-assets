package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.common.exception.custom.CustomAuthrizationException;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginMemberService {

  private final MemberRepository memberRepository;

  public void login(String loginId, String password) {
    Member member = memberRepository.findByEmail(loginId)
        .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

    if (!member.validPassword(password)) {
      throw new CustomAuthrizationException(ErrorCode.NOT_VALID_PASSWORD);
    }
  }
}
