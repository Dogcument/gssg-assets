package com.gssg.gssgbe.web.member.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.dto.request.UpdateMemberDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMemberRequest {

	@Schema(description = "공개되는 이름")
	@NotEmpty
	private String nickname;

	@Schema(description = "프로필 강아지")
	@NotNull
	private ProfileDogType profileDogType;

	@Schema(description = "한줄 소개")
	@NotNull
	private String introduce;

	public UpdateMemberDto toDto() {
		return new UpdateMemberDto(nickname, profileDogType, introduce);
	}
}
