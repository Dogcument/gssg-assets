package com.gssg.gssgbe.web.member.request;

import com.gssg.gssgbe.common.type.ProfileDogType;
import com.gssg.gssgbe.domain.member.dto.request.CreateMemberDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {

    @Schema(description = "이메일 (로그인 ID)")
    @Length(max = 255)
    @Email
    @NotEmpty
    private String email;

    @Schema(description = "비밀번호")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{8,}$",
            message = "비밀번호는 8자 이상의 하나의 문자, 숫자, 특수 문자가 포함된 문자열이어야 합니다.")
    @NotEmpty
    private String password;

    @Schema(description = "공개되는 이름")
    @NotEmpty
    private String nickname;

    @Schema(description = "프로필 강아지")
    @NotNull
    private ProfileDogType profileDogType;

    @Schema(description = "한줄 소개")
    @NotNull
    private String introduce;

    public CreateMemberRequest(final String email, final String password, final String nickname,
                               final ProfileDogType profileDogType, final String introduce) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileDogType = Optional.ofNullable(profileDogType).orElseGet(ProfileDogType::getDefault);
        this.introduce = introduce;
    }

    public CreateMemberDto toDto() {
        return new CreateMemberDto(email, password, nickname, profileDogType, introduce);
    }
}

