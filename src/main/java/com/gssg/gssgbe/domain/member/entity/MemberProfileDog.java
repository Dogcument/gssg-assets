package com.gssg.gssgbe.domain.member.entity;

import com.gssg.gssgbe.common.type.ProfileDogType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberProfileDog {

  @Enumerated(EnumType.STRING)
  @Column(name = "profile_dog", nullable = false)
  private ProfileDogType profileDog;

  public MemberProfileDog(ProfileDogType profileDog) {
    this.profileDog = profileDog;
  }
}
