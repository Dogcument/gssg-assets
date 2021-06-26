package com.gssg.gssgbe.domain.member.service;

import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

  private final CreateMemberService createMemberService;

  public long create(CreateMemberDto createDto) {
    return createMemberService.create(createDto);
  }
}
