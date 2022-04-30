package com.gssg.gssgbe.web.my;

import com.gssg.gssgbe.common.annotation.LoginMember;
import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.BusinessException;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.domain.member.service.UpdateMemberService;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.service.FindPostService;
import com.gssg.gssgbe.web.member.request.UpdateMemberPasswordRequest;
import com.gssg.gssgbe.web.member.request.UpdateMemberRequest;
import com.gssg.gssgbe.web.member.response.MemberResponse;
import com.gssg.gssgbe.web.post.response.FindAllPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 - my")
@Validated
@RequiredArgsConstructor
@RestController
public class MyController {

    private final FindMemberService findMemberService;
    private final UpdateMemberService updateMemberService;
    private final FindPostService findPostService;

    @Operation(summary = "내 회원 정보 조회", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/api/v1/my")
    public MemberResponse myInfo(
        @Parameter(hidden = true) @LoginMember final Member loginMember) {
        final MemberDto memberDto = findMemberService.findById(loginMember.getId())
            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        return new MemberResponse(memberDto);
    }

    @Operation(summary = "내 회원 정보 수정", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/api/v1/my")
    public Long update(
        @Parameter(hidden = true) @LoginMember final Member loginMember,
        @RequestBody final UpdateMemberRequest request) {
        return updateMemberService.update(loginMember.getId(), request.toDto());
    }

    @Operation(summary = "내 회원 비밀번호 수정", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/api/v1/my/password")
    public Long updatePassword(
        @Parameter(hidden = true) @LoginMember final Member loginMember,
        @RequestBody final UpdateMemberPasswordRequest request) {
        return updateMemberService.updatePassword(loginMember.getId(), request.getPassword());
    }

    @Operation(summary = "내 글 조회", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/api/v1/my/posts")
    public FindAllPostResponse findMyPosts(
        @Parameter(hidden = true) @LoginMember final Member loginMember,
        @RequestParam @Nullable @Positive final Long currentPostId,
        @RequestParam(defaultValue = "10") @Positive final Integer size) {
        final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(currentPostId, size);
        final List<PostDto> postDtos = findPostService.findByMember(loginMember, pageRequest);

        return FindAllPostResponse.of(postDtos);
    }
}
