package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.domain.member.dto.request.CreateMemberRequest;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMemberService {

  private final MemberRepository memberRepository;

  public long create(CreateMemberRequest request) {
    Member member = new Member(request.getLoginId(), request.getPassword(), request.getNickName());

    return memberRepository.save(member).getId();
  }
}
