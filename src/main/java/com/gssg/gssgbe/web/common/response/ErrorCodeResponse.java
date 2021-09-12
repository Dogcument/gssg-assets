package com.gssg.gssgbe.web.common.response;

import com.gssg.gssgbe.common.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorCodeResponse {

	@Schema(description = "에러명")
	private String name;

	@Schema(description = "에러 코드")
	private String code;

	@Schema(description = "에러 사유")
	private String reason;

	public ErrorCodeResponse(final ErrorCode errorCode) {
		this.name = errorCode.name();
		this.code = errorCode.getCode();
		this.reason = errorCode.getReason();
	}
}
