package com.gssg.gssgbe.web.member;

import com.gssg.gssgbe.common.clazz.NoOffsetPageRequest;
import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomAuthenticationException;
import com.gssg.gssgbe.domain.member.dto.response.MemberDto;
import com.gssg.gssgbe.domain.member.service.CreateMemberService;
import com.gssg.gssgbe.domain.member.service.FindMemberService;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.service.FindPostService;
import com.gssg.gssgbe.web.member.request.CreateMemberRequest;
import com.gssg.gssgbe.web.post.response.FindAllPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@Validated
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final CreateMemberService createMemberService;
    private final FindMemberService findMemberService;
    private final FindPostService findPostService;

    @Operation(summary = "회원 이메일 존재 여부")
    @GetMapping("/api/v1/members/email/exists")
    public Boolean existsEmail(@RequestParam @Email final String email) {
        return findMemberService.existsEmail(email);
    }

    @Operation(summary = "회원 필명 존재 여부")
    @GetMapping("/api/v1/members/nickname/exists")
    public Boolean existsNickname(@RequestParam final String nickname) {
        return findMemberService.existsNickname(nickname);
    }

    @Operation(summary = "회원 가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/members")
    public Long create(@RequestBody final CreateMemberRequest request) {
        return createMemberService.create(request.toDto());
    }

    @Operation(summary = "다른 사람 정보 조회")
    @GetMapping("/api/v1/member/info")
    public MemberDto getMemberInfo(@RequestParam final String nickname) {
        return findMemberService.findByNickname(nickname)
            .orElseThrow(() -> new CustomAuthenticationException(ErrorCode.NOT_EXISTS_MEMBER));
    }

    @Operation(summary = "다른 사람 글 조회")
    @GetMapping("/api/v1/member/post/")
    public FindAllPostResponse findUserPosts(
        @Parameter final String nickname,
        @RequestParam @Nullable @Positive final Long currentPostId,
        @RequestParam(defaultValue = "10") @Positive final Integer size) {
        final NoOffsetPageRequest pageRequest = NoOffsetPageRequest.of(currentPostId, size);
        final List<PostDto> postDtos = findPostService.findByNickname(nickname, pageRequest);
        return FindAllPostResponse.of(postDtos);
    }
}
