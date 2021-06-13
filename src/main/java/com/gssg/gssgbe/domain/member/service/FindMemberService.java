package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.domain.member.dto.response.MemberResponse;
import com.gssg.gssgbe.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindMemberService {

  private final MemberRepository memberRepository;

  public List<MemberResponse> findAll() {
    return memberRepository.findAll().stream()
        .map(MemberResponse::new)
        .collect(Collectors.toList());
  }
}
