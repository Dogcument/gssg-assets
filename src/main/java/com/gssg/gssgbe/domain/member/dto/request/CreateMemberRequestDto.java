package com.gssg.gssgbe.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberRequestDto {

  private final String email;
  private final String password;
  private final String nickName;
}

