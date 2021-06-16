package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.domain.member.dto.request.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

  private final CreateMemberService createMemberService;

  public long create(CreateMemberRequest request) {
    return createMemberService.create(request);
  }
}
