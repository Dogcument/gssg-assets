package com.gssg.gssgbe.domain.member.dto.response;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberDto {

  private final Long id;
  private final String email;
  private final ProfileDogType profileDog;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public MemberDto(Member member) {
    this.id = member.getId();
    this.email = member.getEmail();
    this.profileDog = member.getProfileDog();
    this.createdAt = member.getCreatedAt();
    this.updatedAt = member.getUpdatedAt();
  }
}
