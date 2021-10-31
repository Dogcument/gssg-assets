package com.gssg.gssgbe.domain.member.dto.response;

import java.time.LocalDateTime;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {

	private final Long id;
	private final String email;
	private final String nickname;
	private final ProfileDogType profileDog;
	private final String introduce;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public static MemberDto of(final Member member) {
		return MemberDto.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.profileDog(ProfileDogType.getDefault())
			.introduce(member.getIntroduce())
			.createdAt(member.getCreatedAt())
			.updatedAt(member.getUpdatedAt())
			.build();
	}
}
