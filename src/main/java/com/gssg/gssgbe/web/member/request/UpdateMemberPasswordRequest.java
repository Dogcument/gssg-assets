package com.gssg.gssgbe.web.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMemberPasswordRequest {

    @Schema(description = "비밀번호")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,}$",
        message = "비밀번호는 8자 이상의 하나의 문자, 숫자, 특수 문자가 포함된 문자열이어야 합니다.")
    @NotEmpty
    private String password;
}
