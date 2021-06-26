package com.gssg.gssgbe.domain.member.dto.request;

import com.gssg.gssgbe.common.type.ProfileDogType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberDto {

  private final String email;
  private final String password;
  private final String nickName;
  private final ProfileDogType profileDogType;
}

