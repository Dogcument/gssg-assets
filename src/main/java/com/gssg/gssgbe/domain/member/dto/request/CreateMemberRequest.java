package com.gssg.gssgbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {

  @Schema(description = "로그인 ID")
  @Length(max = 255)
  @Email
  @NotEmpty
  private String loginId;

  @Schema(description = "비밀번호")
  @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,}$",
      message = "비밀번호는 8자 이상의 하나의 문자, 숫자, 특수 문자가 포함된 문자열이어야 합니다.")
  @NotEmpty
  private String password;

  @Schema(description = "공개되는 이름")
  @NotEmpty
  private String nickName;
}

