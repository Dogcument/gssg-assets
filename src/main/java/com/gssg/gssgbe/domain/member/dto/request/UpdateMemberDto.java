package com.gssg.gssgbe.domain.member.dto.request;

import com.gssg.gssgbe.common.type.ProfileDogType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberDto {

    private final String nickname;
    private final ProfileDogType profileDogType;
    private final String introduce;
}
