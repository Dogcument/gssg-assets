package com.gssg.gssgbe.web.common.response;

import com.gssg.gssgbe.common.type.ProfileDogType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileDogResponse {

	@Schema(description = "영어면")
	private String englishName;

	@Schema(description = "한글명")
	private String koreanName;

	public ProfileDogResponse(final ProfileDogType type) {
		this.englishName = type.name();
		this.koreanName = type.getName();
	}
}
