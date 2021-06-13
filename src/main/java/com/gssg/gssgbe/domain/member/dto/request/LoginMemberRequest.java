package com.gssg.gssgbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginMemberRequest {

  @Schema(description = "로그인 ID")
  @Email
  @NotEmpty
  private String loginId;

  @Schema(description = "비밀번호")
  @NotEmpty
  private String password;
}

