package com.gssg.gssgbe.web.member.response;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    @Schema(description = "회원 PK")
    private Long id;

    @Schema(description = "로그인 ID")
    private String email;

    @Schema(description = "프로필 강아지")
    private ProfileDogType profileDog;

    public MemberResponse(MemberDto dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.profileDog = dto.getProfileDog();
    }
}
