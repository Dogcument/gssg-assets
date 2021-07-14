package com.gssg.gssgbe.web.member;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.service.CreateMemberService;
import com.gssg.gssgbe.domain.member.service.UpdateMemberService;
import com.gssg.gssgbe.web.member.request.CreateMemberRequest;
import com.gssg.gssgbe.web.member.request.UpdateMemberPasswordRequest;
import com.gssg.gssgbe.web.member.request.UpdateMemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@Validated
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final CreateMemberService createMemberService;
    private final UpdateMemberService updateMemberService;

    @Operation(summary = "회원 가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/members")
    public Long create(@RequestBody CreateMemberRequest request) {
        return createMemberService.create(request.toDto());
    }

    @Operation(summary = "회원 정보 수정")
    @PatchMapping("/api/v1/members")
    public Long update(
        @LoginMember Member loginMember,
        @RequestBody UpdateMemberRequest request) {
        return updateMemberService.update(loginMember.getId(), request.toDto());
    }

    @Operation(summary = "회원 비밀번호 수정")
    @PatchMapping("/api/v1/members/password")
    public Long updatePassword(
        @LoginMember Member loginMember,
        @RequestBody UpdateMemberPasswordRequest request) {
        return updateMemberService.updatePassword(loginMember.getId(), request.getPassword());
    }
}
