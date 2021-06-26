package com.gssg.gssgbe.domain.member.service;

import static com.gssg.gssgbe.common.exception.ErrorCode.EXISTS_EMAIL;

import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMemberService {

  private final MemberRepository memberRepository;

  public long create(CreateMemberDto request) {
    validation(request);

    Member member = new Member(request.getEmail(), request.getPassword(), request.getNickName());

    return memberRepository.save(member).getId();
  }

  private void validation(CreateMemberDto request) {
    if (memberRepository.existsByEmail(request.getEmail())) {
      throw new BusinessException(EXISTS_EMAIL);
    }
  }
}
