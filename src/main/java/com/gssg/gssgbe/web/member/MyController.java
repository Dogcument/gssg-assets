package com.gssg.gssgbe.web.member;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.domain.member.service.UpdateMemberService;
import com.gssg.gssgbe.web.member.request.UpdateMemberPasswordRequest;
import com.gssg.gssgbe.web.member.request.UpdateMemberRequest;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 - my")
@RequiredArgsConstructor
@RestController
public class MyController {

    private final FindMemberService findMemberService;
    private final UpdateMemberService updateMemberService;

    @Operation(summary = "내 회원 정보 조회", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/api/v1/my")
    public MemberResponse myInfo(
        @Parameter(hidden = true) @LoginMember Member loginMember) {
        MemberDto memberDto = findMemberService.findById(loginMember.getId())
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        return new MemberResponse(memberDto);
    }

    @Operation(summary = "내 회원 정보 수정", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/api/v1/my")
    public Long update(
        @Parameter(hidden = true) @LoginMember Member loginMember,
        @RequestBody UpdateMemberRequest request) {
        return updateMemberService.update(loginMember.getId(), request.toDto());
    }

    @Operation(summary = "내 회원 비밀번호 수정", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/api/v1/my/password")
    public Long updatePassword(
        @Parameter(hidden = true) @LoginMember Member loginMember,
        @RequestBody UpdateMemberPasswordRequest request) {
        return updateMemberService.updatePassword(loginMember.getId(), request.getPassword());
    }
}
