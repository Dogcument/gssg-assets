package com.gssg.gssgbe.web.member;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 - my")
@RequiredArgsConstructor
@RestController
public class MyController {

    private final FindMemberService findMemberService;

    @Operation(summary = "내 정보 조회")
    @GetMapping("/api/v1/my/info")
    public MemberResponse myInfo(@LoginMember Member loginMember) {
        MemberDto memberDto = findMemberService.findById(loginMember.getId())
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        return new MemberResponse(memberDto);
    }
}
